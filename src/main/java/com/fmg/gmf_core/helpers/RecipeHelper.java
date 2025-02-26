package com.fmg.gmf_core.helpers;

import com.fmg.gmf_core.daos.RecipeDao;
import com.fmg.gmf_core.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class RecipeHelper {
    private final RecipeDao recipeDao;

    public RecipeHelper(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }
    public void recipeExist(int id){
        if (!recipeDao.recipeIdExist(id)){
            throw new ResourceNotFoundException("Recette inexistante");
        }
    }
}
