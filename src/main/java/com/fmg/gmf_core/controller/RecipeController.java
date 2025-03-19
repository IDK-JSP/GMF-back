package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.*;
import com.fmg.gmf_core.dtos.IngredientDetailsDto;
import com.fmg.gmf_core.dtos.NewRecipeDto;
import com.fmg.gmf_core.dtos.RecipeDietsDto;
import com.fmg.gmf_core.entitys.*;
import com.fmg.gmf_core.dtos.RecipeDetailsDto;
import com.fmg.gmf_core.helpers.RecipeHelper;
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
    private final RecipeHelper recipeHelper;
    private final IngredientDao ingredientDao;
    private final FavoriteDao favoriteDao;
    private final DuelDao duelDao;

    public RecipeController(RecipeDao recipeDao, IngredientDetailsDao ingredientDetailsDao, RecipeDietsDao recipeDietsDao, RecipeDietsDao recipeDietsDao1, RecipeDetailsDto recipeDetailsDto, StageDao stageDao, RecipeIngredientDao recipeIngredientDao, JwtUtil jwtUtil, OpinionDao opinionDao, RecipeHelper recipeHelper, IngredientDao ingredientDao, FavoriteDao favoriteDao, DuelDao duelDao) {
        this.recipeDao = recipeDao;
        this.ingredientDetailsDao = ingredientDetailsDao;
        this.recipeDietsDao = recipeDietsDao1;
        this.recipeDetailsDto = recipeDetailsDto;
        this.stageDao = stageDao;
        this.recipeIngredientDao = recipeIngredientDao;
        this.jwtUtil = jwtUtil;
        this.opinionDao = opinionDao;
        this.recipeHelper = recipeHelper;
        this.ingredientDao = ingredientDao;
        this.favoriteDao = favoriteDao;
        this.duelDao = duelDao;
    }


    @GetMapping("/all")
    public ResponseEntity<List<RecipeDietsDto>> getAllRecipes() {
        return ResponseEntity.ok(recipeDietsDao.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeFromId(@PathVariable int id) {
        return ResponseEntity.ok(recipeDao.findRecipeById(id));
    }

    @GetMapping("/user")
    public ResponseEntity<List<RecipeDietsDto>> getUserRecipe(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
        String email = jwtUtil.getEmailFromToken(token);
        return ResponseEntity.ok(recipeDao.findUserRecipe(email));
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
            for (int i = 0; i < newRecipeDto.getStages().size(); i++) {
                newRecipeDto.getStages().get(i).setId_recipe(id_recipe);
                stageDao.save(newRecipeDto.getStages().get(i));
            }
            for (int i = 0; i < newRecipeDto.getRecipeIngredients().size(); i++) {
                newRecipeDto.getRecipeIngredients().get(i).setId_recipe(id_recipe);
                recipeIngredientDao.save(newRecipeDto.getRecipeIngredients().get(i));
            }
            return ResponseEntity.ok(newRecipeDto.getRecipe().getTitle());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Authorization header");
        }
    }

    @DeleteMapping("/delete/{recipeId}")
    public ResponseEntity<String> deleteFavoriteRecipe(@PathVariable int recipeId, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
        String email = jwtUtil.getEmailFromToken(token);  // Appel de votre méthode getEmailFromToken
        recipeHelper.recipeExist(recipeId);
        List<IngredientDetailsDto> ingredients = ingredientDetailsDao.findRecipeIngredients(recipeId);
        for (int i = 0; i < ingredients.size(); i++) {
            recipeIngredientDao.deleteRecipeIngredient(recipeId, (ingredientDao.findIngredientIdByName(ingredients.get(i).getIngredient_name())).getId_ingredient());
        }
        System.out.println("test");
        List<Stage> stages = stageDao.findRecipeStage(recipeId);
        for (int i = 1; i < stages.size()+1; i++) {
            stageDao.deleteStage(recipeId, i);
        }
        List<Opinion> opinions = opinionDao.findRecipeOpinion(recipeId);
        for (int i = 0; i < opinions.size(); i++) {
            opinionDao.deleteOpinion(recipeId, opinions.get(i).getEmail());
        }
        List<Favorite> favorites = favoriteDao.findRecipeFavorite(recipeId);
        for (int i = 1; i < favorites.size(); i++) {
            favoriteDao.deleteFavorite(recipeId,favorites.get(i).getEmail(),"recipe");
        }
        List<Duel> duels = duelDao.findRecipeDuel(recipeId);
        for (int i = 0; i<duels.size();i++){
            duelDao.deletedDuel(recipeId);
        }
        recipeDao.deleteRecipe(recipeId);
        return ResponseEntity.ok("Recette supprimé avec succès.");

    }
}
