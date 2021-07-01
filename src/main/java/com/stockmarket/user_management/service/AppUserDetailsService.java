package com.stockmarket.user_management.service;

import com.stockmarket.user_management.domain.User;
import com.stockmarket.user_management.domain.UserDto;
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

        }
        return null;
    }

}