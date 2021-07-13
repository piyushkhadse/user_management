package com.stockmarket.user_management.security;

import com.stockmarket.user_management.domain.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class AppUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private static final String ROLE_PREFIX = "ROLE_";

    private UserDto userdto;

    public AppUser(UserDto userdto) {
        this.userdto = userdto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(ROLE_PREFIX + userdto.getRole());
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return userdto.getPassword();
    }

    @Override
    public String getUsername() {
        return userdto.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
