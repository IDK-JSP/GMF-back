package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.RecipeDao;
import com.fmg.gmf_core.daos.IngredientDetailsDao;
import com.fmg.gmf_core.daos.StageDao;
import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.entitys.RecipeDetails;
import com.fmg.gmf_core.security.JwtFilter;
import com.fmg.gmf_core.security.JwtUtil;
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
    private final JwtUtil jwtUtil;
    public RecipeController(RecipeDao recipeDao, IngredientDetailsDao ingredientDetailsDao, RecipeDetails recipeDetails, StageDao stageDao, JwtUtil jwtUtil){
        this.recipeDao =recipeDao;
        this.ingredientDetailsDao = ingredientDetailsDao;
        this.recipeDetails = recipeDetails;
        this.stageDao = stageDao;
        this.jwtUtil = jwtUtil;
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
    public ResponseEntity<String> newRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String token){
        System.out.println(jwtUtil.getEmailFromToken(token));
        recipeDao.save(recipe);
        return ResponseEntity.ok(recipe.getTitle());
    }
}
