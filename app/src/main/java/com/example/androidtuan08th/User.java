package com.example.androidtuan08th;

public class User {
    private String name;
    private String email;
    private String image;
    private String feel;

    public User() {
    }

    public User(String name, String email, String image, String feel) {
        this.name = name;
        this.email = email;
        this.image = image;
        this.feel = feel;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }
}
