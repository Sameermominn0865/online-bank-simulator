package com.hendisantika.onlinebanking.service;

import com.hendisantika.onlinebanking.entity.User;

public interface UserService {

    User registerUser(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);
}
