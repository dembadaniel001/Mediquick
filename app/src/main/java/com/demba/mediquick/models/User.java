package com.demba.mediquick.models;

public class User {
private String name;
private String email;
private String role;
private String profile_image;
private String user_id;

    public User(String name, String role, String email, String profile_image, String user_id) {
        this.name = name;
        this.email = email;
        this.profile_image = profile_image;
        this.user_id = user_id;
        this.role = role;
    }
    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
