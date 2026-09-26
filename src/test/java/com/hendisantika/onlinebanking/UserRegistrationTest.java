package com.hendisantika.onlinebanking;

import com.hendisantika.onlinebanking.entity.User;
import com.hendisantika.onlinebanking.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserRegistrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void signupStoresHashedPasswordAndUserRole() {
        String rawPassword = "53cret";
        String username = "user-" + UUID.randomUUID();

        User user = new User();
        user.setUsername(username);
        user.setPassword(rawPassword);
        user.setFirstName("Ada");
        user.setLastName("Lovelace");
        user.setEmail(username + "@example.com");
        user.setPhone("5551112345");

        User saved = userService.registerUser(user);

        assertThat(saved.getUserId()).isNotNull();
        assertThat(saved.getPassword()).isNotEqualTo(rawPassword);
        assertThat(passwordEncoder.matches(rawPassword, saved.getPassword())).isTrue();

        Integer roleId = jdbcTemplate.queryForObject(
                """
                SELECT ur.role_id
                FROM user_role ur
                JOIN `user` u ON u.user_id = ur.user_id
                WHERE u.username = ?
                """,
                Integer.class,
                username);
        assertThat(roleId).isEqualTo(0);
    }
}
