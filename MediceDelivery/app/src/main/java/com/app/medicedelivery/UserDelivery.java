package com.app.medicedelivery;

/**
 * This class has the fields that are in
 * Firebase Firestore
 */
public class UserDelivery {

    String name, email, address, productName;

    public UserDelivery(){}
    public UserDelivery(String name, String email, String address, String productName) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.productName = productName;
    }

    /**
     * Returns name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns productName
     * @return productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Set productName
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
}
