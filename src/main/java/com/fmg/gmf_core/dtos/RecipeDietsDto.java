package com.fmg.gmf_core.dtos;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RecipeDietsDto {
    private int id_recipe;
    private String email;
    private String title;
    private String content;
    private String image;
    private int person;
    private String state;
    private Double rate;
    private int nbRate;
    private int cooking_time;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String diet;
    private String favorite;

    public RecipeDietsDto() {
    }

    public RecipeDietsDto(int id_recipe, String email, String title, String content, String image, int person, String state, Double rate, int nbRate, int cooking_time, LocalDateTime createTime, LocalDateTime updateTime, String diet, String favorite) {
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
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.diet = diet;
        this.favorite = favorite;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}
