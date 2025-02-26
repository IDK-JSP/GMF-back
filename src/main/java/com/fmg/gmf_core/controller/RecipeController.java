package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.RecipeDao;
import com.fmg.gmf_core.entitys.Recipe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeDao recipeDao;
    public RecipeController(RecipeDao recipeDao){
        this.recipeDao =recipeDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Recipe>> getAllRecipe() {
        return ResponseEntity.ok(recipeDao.findAll());
    }

    @PostMapping("/new")
    public ResponseEntity<String> newRecipe(@RequestBody Recipe recipe){
        recipeDao.save(recipe);
        return ResponseEntity.ok(recipe.getTitle());
    }
}
