package com.example.miku_project.models;

public class User {
    private String id, username, avatar_user, email, password;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String id, String username, String avatar_user, String email, String password) {
        this.id = id;
        this.username = username;
        this.avatar_user = avatar_user;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar_user() {
        return avatar_user;
    }

    public void setAvatar_user(String avatar_user) {
        this.avatar_user = avatar_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
