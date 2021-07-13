package com.stockmarket.user_management.controller;

import com.stockmarket.user_management.domain.LoginRequest;
import com.stockmarket.user_management.domain.LoginResponse;
import com.stockmarket.user_management.domain.User;
import com.stockmarket.user_management.logger.StockMarketApplicationLogger;
import com.stockmarket.user_management.security.JwtTokenUtil;
import com.stockmarket.user_management.service.AppUserDetailsService;
import com.stockmarket.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserManagementController {
    @Autowired
    private UserService userService;

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private StockMarketApplicationLogger logger = StockMarketApplicationLogger.getLogger(this.getClass());

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest authenticationRequest) throws Exception {

        logger.info().log("Inside login()");

        Authentication authentication = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = appUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean isMatched = encoder.matches(authenticationRequest.getPassword(),userDetails.getPassword());
        if(isMatched) {
            String token = jwtTokenUtil.generateToken(userDetails);
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(r -> r.getAuthority()).collect(Collectors.toList());
            LoginResponse response = new LoginResponse(token,roles.get(0), Instant.now());
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody User user) {
        logger.info().log("Inside addUser()");
        return new ResponseEntity(userService.addUser(user), HttpStatus.CREATED);
    }

}
