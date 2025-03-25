package com.fmg.gmf_core.entitys;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public class Duel {

    private int id_duel;
    @Min(value = 1, message = "Il vous faut un id")
    private int id_recipe_right;
    @Min(value = 1, message = "Il vous faut un id")
    private int id_recipe_left;
    private String email;
    private LocalDateTime create_time;

    public Duel(int id_duel, int id_recipe_right, int id_recipe_left, String email, LocalDateTime create_time) {
        this.id_duel = id_duel;
        this.id_recipe_right = id_recipe_right;
        this.id_recipe_left = id_recipe_left;
        this.email = email;
        this.create_time = create_time;
    }

    public int getId_duel() {
        return id_duel;
    }

    public void setId_duel(int id_duel) {
        this.id_duel = id_duel;
    }

    public int getId_recipe_right() {
        return id_recipe_right;
    }

    public void setId_recipe_right(int id_recipe_right) {
        this.id_recipe_right = id_recipe_right;
    }

    public int getId_recipe_left() {
        return id_recipe_left;
    }

    public void setId_recipe_left(int id_recipe_left) {
        this.id_recipe_left = id_recipe_left;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }
}
