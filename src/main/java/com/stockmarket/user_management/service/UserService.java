package com.stockmarket.user_management.service;

import com.stockmarket.user_management.domain.AddUserRequest;
import com.stockmarket.user_management.domain.Error;
import com.stockmarket.user_management.domain.User;
import com.stockmarket.user_management.exception.ApplicationException;
import com.stockmarket.user_management.logger.StockMarketApplicationLogger;
import com.stockmarket.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    StockMarketApplicationLogger logger = StockMarketApplicationLogger.getLogger(this.getClass());

    /**
     * creates a user
     * @param user
     * @return
     */
    public Object addUser(AddUserRequest user) {
        User user1 = userRepository.findByUserName(user.getUserName());
        if (user1 != null) {
            logger.error().log("Error in addUser() : User already exists");
            throw new ApplicationException(new Error("INVALID_INPUT","User already exists"),400);
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            User user2 = new User();
            user2.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user2.setUserName(user.getUserName());
            user2.setContactNumber(user.getContactNumber());
            user2.setFirstName(user.getFirstName());
            user2.setLastName(user.getLastName());
            user2.setRole(user.getRole());
            userRepository.save(user2);
            return null;
        }
    }

}