package com.fmg.gmf_core.dtos;

import com.fmg.gmf_core.entitys.Duel;
import com.fmg.gmf_core.entitys.Vote;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


public class DuelDto {
    private  int id_duel;
    private String email;
    private int id_recipe_left;
    private int id_recipe_right;
    private LocalDateTime create_time;
    private int totalVote;
    private int totalRightVote;

    public DuelDto(int id_duel, String email, int id_recipe_left, int id_recipe_right, LocalDateTime create_time, int totalVote, int totalRightVote) {
        this.id_duel = id_duel;
        this.email = email;
        this.id_recipe_left = id_recipe_left;
        this.id_recipe_right = id_recipe_right;
        this.create_time = create_time;
        this.totalVote = totalVote;
        this.totalRightVote = totalRightVote;
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

    public int getId_recipe_left() {
        return id_recipe_left;
    }

    public void setId_recipe_left(int id_recipe_left) {
        this.id_recipe_left = id_recipe_left;
    }

    public int getId_recipe_right() {
        return id_recipe_right;
    }

    public void setId_recipe_right(int id_recipe_right) {
        this.id_recipe_right = id_recipe_right;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public int getTotalVote() {
        return totalVote;
    }

    public void setTotalVote(int totalVote) {
        this.totalVote = totalVote;
    }

    public int getTotalRightVote() {
        return totalRightVote;
    }

    public void setTotalRightVote(int totalRightVote) {
        this.totalRightVote = totalRightVote;
    }
}
