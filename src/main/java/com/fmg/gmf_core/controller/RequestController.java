package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.RecipeDao;
import com.fmg.gmf_core.daos.RecipeIngredientDao;
import com.fmg.gmf_core.daos.RequestDao;
import com.fmg.gmf_core.daos.StageDao;
import com.fmg.gmf_core.dtos.NewRecipeDto;
import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {
    private final RecipeDao recipeDao;
    private final JwtUtil jwtUtil;
    private final RequestDao requestDao;
    private final StageDao stageDao;
    private final RecipeIngredientDao recipeIngredientDao;

    public RequestController(RecipeDao recipeDao, JwtUtil jwtUtil, RequestDao requestDao, StageDao stageDao, RecipeIngredientDao recipeIngredientDao) {
        this.recipeDao = recipeDao;
        this.jwtUtil = jwtUtil;
        this.requestDao = requestDao;
        this.stageDao = stageDao;
        this.recipeIngredientDao = recipeIngredientDao;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Recipe>> allRecipeToValidate(@RequestHeader("Authorization") String authorizationHeader){
        return ResponseEntity.ok(requestDao.findAll());
    }
    @PostMapping("/recipe")
    public ResponseEntity<Recipe> validRecipe(@Valid @RequestBody Recipe recipe, @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            String email = jwtUtil.getEmailFromToken(token);  // Appel de votre méthode getEmailFromToken
            System.out.println("Email extrait du token : " + email);
            recipe.setEmail(email);
            recipe.setState("validate");
            int id_recipe = recipeDao.updateRecipe(recipe);
            return ResponseEntity.ok(recipe);
        } else {
            return null;
        }
    }
}
