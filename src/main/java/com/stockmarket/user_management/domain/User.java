package com.stockmarket.user_management.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    int id;

    @NotNull
    @Column(name = "username")
    String userName;

    @NotNull
    @Column(name = "first_name")
    String firstName;

    @NotNull
    @Column(name = "last_name")
    String lastName;

    @NotNull
    @Column(name = "contact_no")
    String contactNumber;


    @NotNull
    @Column(name = "password")
    String password;

    @NotNull
    @Column(name = "role")
    String role;

    public User() {
        super();
    }

    public User(@NotNull int id, @NotNull String userName, @NotNull String password, @NotNull String firstName,
                @NotNull String lastName, @NotNull String contactNumber) {
        super();
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.role = "U";
    }

}