package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.RecipeDao;
import com.fmg.gmf_core.daos.RecipeDetailsDao;
import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.entitys.RecipeDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeDao recipeDao;
    private final RecipeDetailsDao recipeDetailsDao;
    public RecipeController(RecipeDao recipeDao, RecipeDetailsDao recipeDetailsDao){
        this.recipeDao =recipeDao;
        this.recipeDetailsDao = recipeDetailsDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Recipe>> getAllRecipe() {
        return ResponseEntity.ok(recipeDao.findAll());
    }
    @GetMapping("/details/{id}")
    public ResponseEntity<List<RecipeDetails>> getRecipeDetails(@PathVariable int id){
        return  ResponseEntity.ok(recipeDao.findRecipeIngredients(id));
    }

    @PostMapping("/new")
    public ResponseEntity<String> newRecipe(@RequestBody Recipe recipe){
        recipeDao.save(recipe);
        return ResponseEntity.ok(recipe.getTitle());
    }
}
