package com.fmg.gmf_core.dtos;


import com.fmg.gmf_core.entitys.Stage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeDetailsDto {
    private List<IngredientDetailsDto> ingredientDetailDtos;

    private List<Stage> stages;

    public RecipeDetailsDto(List<IngredientDetailsDto> ingredientDetailDtos, List<Stage> stages) {
        this.ingredientDetailDtos = ingredientDetailDtos;
        this.stages = stages;
    }

    public List<IngredientDetailsDto> getIngredientDetails() {
        return ingredientDetailDtos;
    }

    public void setIngredientDetails(List<IngredientDetailsDto> ingredientDetailDtos) {
        this.ingredientDetailDtos = ingredientDetailDtos;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }
}
