package com.fmg.gmf_core.entitys;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class Recipe {

    private int id_recipe;

    private String email;
    @NotBlank(message = "Un titre de recette est requis")
    private String title;

    private String content;

    private String image;
    @NotNull(message = "Un nombre de personne est requis")
    private Integer person;

    private String state;

    private Double rate;

    private int nbRate;

    @NotNull(message = "Un temps de préparation est requis")
    private int cooking_time;

    private LocalDateTime create_time;

    private LocalDateTime update_time;

    public Recipe(int id_recipe, String email, String title, String content, String image, int person, String state, Double rate, int nbRate, int cooking_time, LocalDateTime create_time, LocalDateTime update_time) {
        this.id_recipe = id_recipe;
        this.email = email;
        this.title = title;
        this.content = content;
        this.image = image;
        this.person = person;
        this.state = state;
        this.rate = rate;
        this.nbRate = nbRate;
        this.cooking_time = cooking_time;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public int getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(int id_recipe) {
        this.id_recipe = id_recipe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public int getNbRate() {
        return nbRate;
    }

    public void setNbRate(int nbRate) {
        this.nbRate = nbRate;
    }

    public int getCooking_time() {
        return cooking_time;
    }

    public void setCooking_time(int cooking_time) {
        this.cooking_time = cooking_time;
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
