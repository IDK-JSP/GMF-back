package com.fmg.gmf_core.entitys;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class User {

    @NotBlank(message = "Email requis")
    private String email;

    @NotBlank(message = "Mot de passe requis")
    private String password;

    private String role;

    @NotBlank(message = "Pseudo requis")
    private String pseudo;


    private String image;

    //Vege vegan
    private String dietery;


    private LocalDateTime create;


    private LocalDateTime update;

    public User(){}

    public User(String email, String password, String role, String pseudo, String image, String dietery, LocalDateTime create, LocalDateTime update) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.pseudo = pseudo;
        this.image = image;
        this.dietery = dietery;
        this.create = create;
        this.update = update;
    }
    public User(String email, String password, String pseudo) {
        this.email = email;
        this.password = password;
        this.pseudo = pseudo;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDiatery() {
        return dietery;
    }

    public void setDiatery(String dietery) {
        this.dietery = dietery;
    }

    public LocalDateTime getCreate() {
        return create;
    }

    public void setCreate(LocalDateTime create) {
        this.create = create;
    }

    public LocalDateTime getUpdate() {
        return update;
    }

    public void setUpdate(LocalDateTime update) {
        this.update = update;
    }
}
