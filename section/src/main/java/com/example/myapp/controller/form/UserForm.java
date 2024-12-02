package com.example.myapp.controller.form;

import jakarta.validation.constraints.NotBlank;

public class UserForm {
    @NotBlank
    public String name;
    @NotBlank
    public String phoneNumber;

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

}
