package com.example.secureapp.model;

public class User {

    private String username;
    private String password;
    private String role;

    private String firstName;
    private String lastName;
    private String email;

    public User(
            String username,
            String password,
            String role,
            String firstName,
            String lastName,
            String email
    ) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
