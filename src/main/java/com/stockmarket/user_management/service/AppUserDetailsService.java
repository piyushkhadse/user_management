package com.stockmarket.user_management.service;

import com.stockmarket.user_management.domain.Error;
import com.stockmarket.user_management.domain.User;
import com.stockmarket.user_management.domain.UserDto;
import com.stockmarket.user_management.exception.ApplicationException;
import com.stockmarket.user_management.logger.StockMarketApplicationLogger;
import com.stockmarket.user_management.repository.UserRepository;
import com.stockmarket.user_management.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Override
    public String toString() {
        return "AppUserDetailsService [userRepository=" + userRepository + "]";
    }

    @Autowired
    UserRepository userRepository;

    private StockMarketApplicationLogger logger = StockMarketApplicationLogger.getLogger(this.getClass());

    /**
     * returns UserDetails
     * @param name
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUserName(name);
            UserDto userdto = new UserDto(user.getUserName(), user.getPassword(), user.getRole());
            AppUser appUser = new AppUser(userdto);
            return appUser;
        } catch (Exception e) {
            logger.error().log("Error while finding user by username:{}",name,e);
            throw new ApplicationException(new Error("INTERNAL_SERVER_ERROR", "Internal Server Error"),500);
        }
    }

}