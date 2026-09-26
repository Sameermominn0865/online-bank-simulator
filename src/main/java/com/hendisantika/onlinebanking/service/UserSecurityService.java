package com.hendisantika.onlinebanking.service;

import com.hendisantika.onlinebanking.entity.User;
import com.hendisantika.onlinebanking.repository.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserDao userDao;

    public UserSecurityService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return user;
    }
}
