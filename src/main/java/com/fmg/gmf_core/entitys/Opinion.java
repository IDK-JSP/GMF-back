package com.fmg.gmf_core.entitys;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class Opinion {

    @NotNull(message = "Un id de recette est requis")
    private int id_recipe;

    private String email;

    @NotNull(message = "La note ne peut pas être nulle")
    @Min(value = 1, message = "La note doit être au minimum 1")
    @Max(value = 5, message = "La note doit être au maximum 5")
    private int rate;
    private String comment;
    private LocalDateTime create_time;

    public Opinion(int id_recipe, String email, int rate, String comment, LocalDateTime createTime) {
        this.id_recipe = id_recipe;
        this.email = email;
        this.rate = rate;
        this.comment = comment;
        create_time = createTime;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }
}
