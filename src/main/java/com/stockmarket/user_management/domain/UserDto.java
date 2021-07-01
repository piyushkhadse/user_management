package com.stockmarket.user_management.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String userName;
    private String password;
    private String role;

    public UserDto() {
        super();
    }

    public UserDto(String userName, String password, String role) {
        super();
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

}