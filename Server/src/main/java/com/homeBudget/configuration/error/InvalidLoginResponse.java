package com.homeBudget.configuration.error;

public class InvalidLoginResponse {
    private String email;
    private String password;

    public InvalidLoginResponse() {
        this.email = "Invalid Email";
        this.password = "Invalid Password";
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
