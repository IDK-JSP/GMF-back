package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.*;
import com.fmg.gmf_core.dtos.NewRecipeDto;
import com.fmg.gmf_core.dtos.RecipeDietsDto;
import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.dtos.RecipeDetailsDto;
import com.fmg.gmf_core.entitys.RecipeIngredient;
import com.fmg.gmf_core.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeDao recipeDao;
    private final IngredientDetailsDao ingredientDetailsDao;
    private final RecipeDietsDao recipeDietsDao;
    private final RecipeDetailsDto recipeDetailsDto;
    private final StageDao stageDao;
    private final RecipeIngredientDao recipeIngredientDao;
    private final JwtUtil jwtUtil;
    private final OpinionDao opinionDao;

    public RecipeController(RecipeDao recipeDao, IngredientDetailsDao ingredientDetailsDao, RecipeDietsDao recipeDietsDao, RecipeDietsDao recipeDietsDao1, RecipeDetailsDto recipeDetailsDto, StageDao stageDao, RecipeIngredientDao recipeIngredientDao, JwtUtil jwtUtil, OpinionDao opinionDao) {
        this.recipeDao = recipeDao;
        this.ingredientDetailsDao = ingredientDetailsDao;
        this.recipeDietsDao = recipeDietsDao1;
        this.recipeDetailsDto = recipeDetailsDto;
        this.stageDao = stageDao;
        this.recipeIngredientDao = recipeIngredientDao;
        this.jwtUtil = jwtUtil;
        this.opinionDao = opinionDao;
    }


    @GetMapping("/all")
    public ResponseEntity<List<RecipeDietsDto>> getAllRecipes() {
        return ResponseEntity.ok(recipeDietsDao.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeFromId(@PathVariable int id) {
        return ResponseEntity.ok(recipeDao.findRecipeById(id));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<RecipeDetailsDto> getRecipeDetails(@PathVariable int id) {
        recipeDetailsDto.setIngredientDetailDtos(ingredientDetailsDao.findRecipeIngredients(id));
        recipeDetailsDto.setStages(stageDao.findRecipeStage(id));
        recipeDetailsDto.setOpinions(opinionDao.findRecipeOpinion(id));
        return ResponseEntity.ok(recipeDetailsDto);
    }

    @PostMapping("/new")
    public ResponseEntity<String> newRecipe(@Valid @RequestBody NewRecipeDto newRecipeDto, @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            String email = jwtUtil.getEmailFromToken(token);  // Appel de votre méthode getEmailFromToken
            System.out.println("Email extrait du token : " + email);
            newRecipeDto.getRecipe().setEmail(email);
            int id_recipe = recipeDao.save(newRecipeDto.getRecipe());
            for (int i = 0; i < newRecipeDto.getStages().size(); i++){
                newRecipeDto.getStages().get(i).setId_recipe(id_recipe);
                stageDao.save(newRecipeDto.getStages().get(i));
            }
            for (int i = 0; i < newRecipeDto.getRecipeIngredients().size(); i++){
                newRecipeDto.getRecipeIngredients().get(i).setId_recipe(id_recipe);
                recipeIngredientDao.save(newRecipeDto.getRecipeIngredients().get(i));
            }
            return ResponseEntity.ok(newRecipeDto.getRecipe().getTitle());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Authorization header");
        }
    }
}
