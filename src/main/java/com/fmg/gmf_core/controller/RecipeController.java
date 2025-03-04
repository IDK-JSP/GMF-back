package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.*;
import com.fmg.gmf_core.dtos.RecipeDietsDto;
import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.dtos.RecipeDetailsDto;
import com.fmg.gmf_core.security.JwtUtil;
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
    private final JwtUtil  jwtUtil;
    private final OpinionDao opinionDao;
    public RecipeController(RecipeDao recipeDao, IngredientDetailsDao ingredientDetailsDao, RecipeDietsDao recipeDietsDao, RecipeDietsDao recipeDietsDao1, RecipeDetailsDto recipeDetailsDto, StageDao stageDao, JwtUtil jwtUtil, OpinionDao opinionDao){
        this.recipeDao =recipeDao;
        this.ingredientDetailsDao = ingredientDetailsDao;
        this.recipeDietsDao = recipeDietsDao1;
        this.recipeDetailsDto = recipeDetailsDto;
        this.stageDao = stageDao;
        this.jwtUtil = jwtUtil;
        this.opinionDao = opinionDao;
    }


    @GetMapping("/all")
    public ResponseEntity<List<RecipeDietsDto>> getAllRecipes() {
        return ResponseEntity.ok(recipeDietsDao.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeFromId(@PathVariable int id){
        return ResponseEntity.ok(recipeDao.findRecipeById(id));
    }
    @GetMapping("/details/{id}")
    public ResponseEntity<RecipeDetailsDto> getRecipeDetails(@PathVariable int id){
        recipeDetailsDto.setIngredientDetailDtos(ingredientDetailsDao.findRecipeIngredients(id));
        recipeDetailsDto.setStages(stageDao.findRecipeStage(id));
        recipeDetailsDto.setOpinions(opinionDao.findRecipeOpinion(id));
        return  ResponseEntity.ok(recipeDetailsDto);
    }
    @GetMapping("/search")
    public List<Recipe> searchRecipes(@RequestParam String name) {
        return recipeDao.findRecipeByName(name);
    }
    @PostMapping("/new")
    public ResponseEntity<String> newRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String authorizationHeader) {
        // Vérifier que l'en-tête Authorization est bien présent et commence par "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extraire le jeton à partir de l'en-tête Authorization
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)

            // Utiliser le token pour récupérer l'email
            String email = jwtUtil.getEmailFromToken(token);  // Appel de votre méthode getEmailFromToken
            System.out.println("Email extrait du token : " + email);
            recipe.setEmail(email);
            // Sauvegarder la recette dans la base de données
            recipeDao.save(recipe);

            // Retourner la réponse avec le titre de la recette
            return ResponseEntity.ok(recipe.getTitle());
        } else {
            // Si l'en-tête Authorization est manquant ou mal formé
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Authorization header");
        }
    }
    // Controller pour ajouter les régimes aux recettes

}
