package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.RecipeDao;
import com.fmg.gmf_core.daos.IngredientDetailsDao;
import com.fmg.gmf_core.daos.StageDao;
import com.fmg.gmf_core.dtos.RecipeDietsDto;
import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.entitys.RecipeDetails;
import com.fmg.gmf_core.security.JwtFilter;
import com.fmg.gmf_core.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fmg.gmf_core.daos.RecipeDietsDao;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeDao recipeDao;
    private final IngredientDetailsDao ingredientDetailsDao;
    private final RecipeDietsDao recipeDietsDao;
    private final RecipeDetails recipeDetails;
    private final StageDao stageDao;
    private final JwtUtil  jwtUtil;
    public RecipeController(RecipeDao recipeDao, IngredientDetailsDao ingredientDetailsDao, RecipeDietsDao recipeDietsDao, RecipeDietsDao recipeDietsDao1, RecipeDetails recipeDetails, StageDao stageDao, JwtUtil jwtUtil){
        this.recipeDao =recipeDao;
        this.ingredientDetailsDao = ingredientDetailsDao;
        this.recipeDietsDao = recipeDietsDao1;
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
    @GetMapping("/diet/all")
    public ResponseEntity<List<RecipeDietsDto>> getAllRecipes() {
        return ResponseEntity.ok(recipeDietsDao.findAll());
    }

}
