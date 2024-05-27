package com.app.medicedelivery;

public class UserDelivery {

    String name, email, address, productName;

    public UserDelivery(){}
    public UserDelivery(String name, String email, String address, String productName) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.productName = productName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
