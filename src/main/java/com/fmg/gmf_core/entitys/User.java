package com.fmg.gmf_core.entitys;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class User {

    @NotBlank(message = "Email requis")
    private String email;

    @NotBlank(message = "Mot de passe requis")
    private String password;

    private String role;


    private String pseudo;


    private String image;


    private LocalDateTime create_time;


    private LocalDateTime update_time;

    public User() {
    }

    public User(String email, String password, String role, String pseudo, String image, LocalDateTime create_time, LocalDateTime update_time) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.pseudo = pseudo;
        this.image = image;
        this.create_time = create_time;
        this.update_time = update_time;
    }
    public User(String email, String password){
        this.email = email;
        this.password = password;
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

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }
}