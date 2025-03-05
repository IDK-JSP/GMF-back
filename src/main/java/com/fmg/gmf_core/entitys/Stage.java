package com.fmg.gmf_core.entitys;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Stage {

    @NotNull(message = "Un numéro d'étape est requis")
    private int stage;
    private int id_recipe;
    @NotBlank(message = "Un contenu d'étape est requis")
    private String content;

    public Stage(int stage, int id_recipe, String content) {
        this.stage = stage;
        this.id_recipe = id_recipe;
        this.content = content;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(int id_recipe) {
        this.id_recipe = id_recipe;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
