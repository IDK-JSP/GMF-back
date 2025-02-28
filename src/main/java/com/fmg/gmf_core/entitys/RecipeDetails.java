package com.fmg.gmf_core.entitys;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeDetails {
    private List<IngredientDetails> ingredientDetails;

    private List<Stage> stages;

    public RecipeDetails(List<IngredientDetails> ingredientDetails, List<Stage> stages) {
        this.ingredientDetails = ingredientDetails;
        this.stages = stages;
    }

    public List<IngredientDetails> getIngredientDetails() {
        return ingredientDetails;
    }

    public void setIngredientDetails(List<IngredientDetails> ingredientDetails) {
        this.ingredientDetails = ingredientDetails;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }
}
