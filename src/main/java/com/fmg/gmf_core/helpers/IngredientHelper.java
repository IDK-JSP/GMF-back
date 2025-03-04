package com.fmg.gmf_core.helpers;

import com.fmg.gmf_core.daos.IngredientDao;
import com.fmg.gmf_core.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class IngredientHelper {
    private final IngredientDao ingredientDao;

    public IngredientHelper(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
    public void ingredientExist(int id){
        if (!ingredientDao.ingredientExist(id)){
            throw new ResourceNotFoundException("Ingrédient "+id+" innexistant");
        }

    }
}
