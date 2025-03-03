package com.fmg.gmf_core.entitys;

public class FavoriteIngredient {
    private int id_ingredient;

    private String name;

    private String content;

    public FavoriteIngredient(int id_ingredient, String name, String content) {
        this.id_ingredient = id_ingredient;
        this.name = name;
        this.content = content;
    }


    public int getIngredient_id() {
        return id_ingredient;
    }

    public void setIngredient_id(int id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
