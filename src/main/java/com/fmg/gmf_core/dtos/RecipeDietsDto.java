package com.fmg.gmf_core.dtos;

import com.fmg.gmf_core.entitys.Diet;

import java.time.LocalDateTime;
import java.util.List;

public class RecipeDietsDto {
    private int id;
    private String email;
    private String title;
    private String content;
    private String image;
    private int person;
    private String state;
    private Double rate;
    private int nbRate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<Diet> diets;

    public RecipeDietsDto() {}

    public RecipeDietsDto(int id, String email, String title, String content, String image, int person, String state, Double rate, int nbRate, LocalDateTime createTime, LocalDateTime updateTime, List<Diet> diets) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.content = content;
        this.image = image;
        this.person = person;
        this.state = state;
        this.rate = rate;
        this.nbRate = nbRate;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.diets = diets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Diet> getDiets() {
        return diets;
    }

    public void setDiets(List<Diet> diets) {
        this.diets = diets;
    }
}
