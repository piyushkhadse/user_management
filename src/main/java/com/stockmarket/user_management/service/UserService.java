package com.stockmarket.user_management.service;

import com.stockmarket.user_management.domain.User;
import com.stockmarket.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * creates a user
     * @param user
     * @return
     */
    public Object addUser(User user) {
        User user1 = userRepository.findByUserName(user.getUserName());
        if (user1 != null) {
            return "User already exists";
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return null;
        }
    }

}