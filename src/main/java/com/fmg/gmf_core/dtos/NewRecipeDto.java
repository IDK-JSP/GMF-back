package com.fmg.gmf_core.dtos;

import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.entitys.RecipeIngredient;
import com.fmg.gmf_core.entitys.Stage;

import java.util.List;

public class NewRecipeDto {
    private Recipe recipe;
    private List<Stage> stages;
    private List<RecipeIngredient> recipeIngredients;

    public NewRecipeDto(Recipe recipe, List<Stage> stages, List<RecipeIngredient> recipeIngredients) {
        this.recipe = recipe;
        this.stages = stages;
        this.recipeIngredients = recipeIngredients;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
}
