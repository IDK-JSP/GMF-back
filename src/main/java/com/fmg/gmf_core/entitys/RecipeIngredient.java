package com.fmg.gmf_core.entitys;

public class RecipeIngredient {
    private int id_recipe;
    private int id_ingredient;
    private int quantity;
    private int id_measurement;

    public RecipeIngredient(int id_recipe, int id_ingredient, int quantity, int id_measurement) {
        this.id_recipe = id_recipe;
        this.id_ingredient = id_ingredient;
        this.quantity = quantity;
        this.id_measurement = id_measurement;
    }

    public int getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(int id_recipe) {
        this.id_recipe = id_recipe;
    }

    public int getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(int id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId_measurement() {
        return id_measurement;
    }

    public void setId_measurement(int id_measurement) {
        this.id_measurement = id_measurement;
    }
}
