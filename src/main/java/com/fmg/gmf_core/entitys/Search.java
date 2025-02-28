package com.fmg.gmf_core.entitys;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Search {
    private List<Ingredient> ingredients;

    private List<Recipe> recipes;

    public Search(List<Ingredient> ingredients, List<Recipe> recipes) {
        this.ingredients = ingredients;
        this.recipes = recipes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
