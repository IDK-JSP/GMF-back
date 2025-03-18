package com.fmg.gmf_core.entitys;

import java.time.LocalDateTime;

public class Vote {

    private int id_duel;
    private String email;
    private int id_recipe;
    private LocalDateTime create_time;

    public Vote(int id_duel, String email, int id_recipe, LocalDateTime create_time) {
        this.id_duel = id_duel;
        this.email = email;
        this.id_recipe = id_recipe;
        this.create_time = create_time;
    }

    public int getId_duel() {
        return id_duel;
    }

    public void setId_duel(int id_duel) {
        this.id_duel = id_duel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(int id_recipe) {
        this.id_recipe = id_recipe;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }
}
