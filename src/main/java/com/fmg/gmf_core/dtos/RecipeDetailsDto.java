package com.fmg.gmf_core.dtos;


import com.fmg.gmf_core.entitys.Opinion;
import com.fmg.gmf_core.entitys.Stage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeDetailsDto {
    private RecipeDietsDto recipeDietsDto;

    private List<IngredientDetailsDto> ingredientDetailDtos;

    private List<Stage> stages;

    private List<Opinion> opinions;

    public RecipeDetailsDto(RecipeDietsDto recipeDietsDto, List<IngredientDetailsDto> ingredientDetailDtos, List<Stage> stages, List<Opinion> opinions) {
        this.recipeDietsDto = recipeDietsDto;
        this.ingredientDetailDtos = ingredientDetailDtos;
        this.stages = stages;
        this.opinions = opinions;
    }

    public List<IngredientDetailsDto> getIngredientDetailDtos() {
        return ingredientDetailDtos;
    }

    public void setIngredientDetailDtos(List<IngredientDetailsDto> ingredientDetailDtos) {
        this.ingredientDetailDtos = ingredientDetailDtos;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public List<Opinion> getOpinions() {
        return opinions;
    }

    public void setOpinions(List<Opinion> opinions) {
        this.opinions = opinions;
    }

    public RecipeDietsDto getRecipeDietsDto() {
        return recipeDietsDto;
    }

    public void setRecipeDietsDto(RecipeDietsDto recipeDietsDto) {
        this.recipeDietsDto = recipeDietsDto;
    }
}
