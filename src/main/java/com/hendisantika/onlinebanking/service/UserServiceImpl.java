package com.hendisantika.onlinebanking.service;

import com.hendisantika.onlinebanking.entity.User;
import com.hendisantika.onlinebanking.repository.RoleDao;
import com.hendisantika.onlinebanking.repository.UserDao;
import com.hendisantika.onlinebanking.security.Role;
import com.hendisantika.onlinebanking.security.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        if (checkUsernameExists(user.getUsername())) {
            throw new IllegalArgumentException("That username is already registered.");
        }
        if (checkEmailExists(user.getEmail())) {
            throw new IllegalArgumentException("That email is already registered.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        Role userRole = roleDao.findByName("ROLE_USER");
        user.getUserRoles().add(new UserRole(user, userRole));
        return userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean checkUsernameExists(String username) {
        return findByUsername(username) != null;
    }

    @Override
    public boolean checkEmailExists(String email) {
        return findByEmail(email) != null;
    }
}
