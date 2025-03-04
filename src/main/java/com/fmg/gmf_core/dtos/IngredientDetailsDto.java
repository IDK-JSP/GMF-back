package com.fmg.gmf_core.dtos;

public class IngredientDetailsDto {
    private String ingredient_name;

    private int quantity;

    private String measurement;

    private String diet;

    public IngredientDetailsDto(String ingredient_name, int quantity, String measurement, String diet) {
        this.ingredient_name = ingredient_name;
        this.quantity = quantity;
        this.measurement = measurement;
        this.diet = diet;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }
}
