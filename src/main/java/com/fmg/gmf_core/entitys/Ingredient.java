package com.fmg.gmf_core.entitys;

import java.time.LocalDateTime;

public class Ingredient {
    private int id_ingredient;

    private String name;

    private String Content;

    private LocalDateTime create_time;

    private LocalDateTime update_time;


    public Ingredient(int id_ingredient, String name, String content, LocalDateTime create_time, LocalDateTime update_time) {
        this.id_ingredient = id_ingredient;
        this.name = name;
        Content = content;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public int getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(int id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
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
