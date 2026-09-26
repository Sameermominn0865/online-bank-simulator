package com.hendisantika.onlinebanking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void dashboardRequiresLoginAndAdminPageRejectsUser() throws Exception {
        mockMvc.perform(get("/userFront"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        String username = "login-" + UUID.randomUUID();
        mockMvc.perform(post("/signup")
                        .param("username", username)
                        .param("password", "53cret")
                        .param("firstName", "Ada")
                        .param("lastName", "Lovelace")
                        .param("email", username + "@example.com")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk());

        MvcResult login = mockMvc.perform(post("/login")
                        .param("username", username)
                        .param("password", "53cret")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/userFront"))
                .andReturn();

        MockHttpSession session = (MockHttpSession) login.getRequest().getSession(false);

        mockMvc.perform(get("/userFront").session(session))
                .andExpect(status().isOk());

        mockMvc.perform(get("/admin").session(session))
                .andExpect(status().isForbidden());
    }
}
