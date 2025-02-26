package com.fmg.gmf_core.entitys;

import java.time.LocalDateTime;

public class Ingredient {
    private int id_ingredient;

    private String name;

    private String Content;

    private LocalDateTime create;

    private LocalDateTime update;

    public Ingredient(int id_ingredient, String name, String content, LocalDateTime create, LocalDateTime update) {
        this.id_ingredient = id_ingredient;
        this.name = name;
        Content = content;
        this.create = create;
        this.update = update;
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
