package com.fmg.gmf_core.dtos;

import com.fmg.gmf_core.entitys.Ingredient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Search {
    private List<Ingredient> ingredients;

    private List<SearchResultRecipeDto> recipes;

    public Search(List<Ingredient> ingredients, List<SearchResultRecipeDto> recipes) {
        this.ingredients = ingredients;
        this.recipes = recipes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<SearchResultRecipeDto> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<SearchResultRecipeDto> recipes) {
        this.recipes = recipes;
    }
}
