package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.IngredientDao;
import com.fmg.gmf_core.daos.RecipeDao;
import com.fmg.gmf_core.entitys.Ingredient;
import com.fmg.gmf_core.entitys.Search;
import com.fmg.gmf_core.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    private final IngredientDao ingredientDao;
    private final RecipeDao recipeDao;
    private final Search search;

    public SearchController(IngredientDao ingredientDao, RecipeDao recipeDao, Search search) {
        this.ingredientDao = ingredientDao;
        this.recipeDao = recipeDao;
        this.search = search;
    }
    @GetMapping("/search")
    public Search searchIngredient(@RequestParam String name) {
        search.setIngredients(ingredientDao.findIngredientByName(name));
        search.setRecipes(recipeDao.findRecipeByName(name));
        if (search.getRecipes().isEmpty() && search.getIngredients().isEmpty()){
            throw new ResourceNotFoundException("Aucun résultat de recherche");
        }
        return search;
    }
}
