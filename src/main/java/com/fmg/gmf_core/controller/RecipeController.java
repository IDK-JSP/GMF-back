package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.RecipeDao;
import com.fmg.gmf_core.daos.IngredientDetailsDao;
import com.fmg.gmf_core.daos.StageDao;
import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.entitys.RecipeDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeDao recipeDao;
    private final IngredientDetailsDao ingredientDetailsDao;
    private final RecipeDetails recipeDetails;
    private final StageDao stageDao;
    public RecipeController(RecipeDao recipeDao, IngredientDetailsDao ingredientDetailsDao, RecipeDetails recipeDetails, StageDao stageDao){
        this.recipeDao =recipeDao;
        this.ingredientDetailsDao = ingredientDetailsDao;
        this.recipeDetails = recipeDetails;
        this.stageDao = stageDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Recipe>> getAllRecipe() {
        return ResponseEntity.ok(recipeDao.findAll());
    }
    @GetMapping("/details/{id}")
    public ResponseEntity<RecipeDetails> getRecipeDetails(@PathVariable int id){
        recipeDetails.setIngredientDetails(ingredientDetailsDao.findRecipeIngredients(id));
        recipeDetails.setStages(stageDao.findRecipeStage(id));
        return  ResponseEntity.ok(recipeDetails);
    }
    @GetMapping("/search")
    public List<Recipe> searchRecipes(@RequestParam String name) {
        return recipeDao.findRecipeByName(name);
    }
    @PostMapping("/new")
    public ResponseEntity<String> newRecipe(@RequestBody Recipe recipe){
        recipeDao.save(recipe);
        return ResponseEntity.ok(recipe.getTitle());
    }
}
