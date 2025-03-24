package com.fmg.gmf_core.dtos;

import com.fmg.gmf_core.entitys.Ingredient;
import com.fmg.gmf_core.entitys.Recipe;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFavoriteDto {
    private List<Ingredient> ingredients;
    private List<RecipeDietsDto> recipes;

    public UserFavoriteDto(List<Ingredient> ingredients, List<RecipeDietsDto> recipes) {
        this.ingredients = ingredients;
        this.recipes = recipes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<RecipeDietsDto> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDietsDto> recipes) {
        this.recipes = recipes;
    }
}
