package com.zakiis.springboot.test.model;

import com.zakiis.security.annotation.Cipher;

/**
 * Table name: user
 * 2022-03-29 10:41:43
 */
public class User {
    private Long id;

    private String username;
    @Cipher
    private String password;

    private String name;

    /** residence address reference table address */
    private Long resAddressId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getResAddressId() {
        return resAddressId;
    }

    public void setResAddressId(Long resAddressId) {
        this.resAddressId = resAddressId;
    }
}