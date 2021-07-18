package com.stockmarket.user_management.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AddUserRequest {
    String userName;
    String firstName;
    String lastName;
    String contactNumber;
    String password;
    String role;
}
