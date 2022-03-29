package com.zakiis.springboot.test.model;

import com.zakiis.security.annotation.Cipher;

/**
 * Table name: address
 * 2022-03-29 10:41:43
 */
public class Address {
    private Long id;

    private String country;

    private String province;

    private String city;

    @Cipher
    private String region;
    @Cipher
    private String street;
    @Cipher
    private String details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}