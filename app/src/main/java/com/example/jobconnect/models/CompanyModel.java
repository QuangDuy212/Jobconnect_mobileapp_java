package com.example.jobconnect.models;

import java.io.Serializable;

public class CompanyModel implements Serializable {
    private String name;
    private String field;
    private String tax;
    private String address;
    private String phone;
    private String email;
    private String description;
    private String products;

    public CompanyModel(String name, String field, String tax, String address, String phone, String email, String description, String products) {
        this.name = name;
        this.field = field;
        this.tax = tax;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.products = products;
    }

    // Getter
    public String getName() { return name; }
    public String getField() { return field; }
    public String getTax() { return tax; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getDescription() { return description; }
    public String getProducts() { return products; }
}
