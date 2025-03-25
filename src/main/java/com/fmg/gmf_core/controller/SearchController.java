package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.IngredientDao;
import com.fmg.gmf_core.daos.RecipeDao;
import com.fmg.gmf_core.daos.SearchDao;
import com.fmg.gmf_core.dtos.Search;
import com.fmg.gmf_core.exceptions.ResourceNotFoundException;
import com.fmg.gmf_core.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final IngredientDao ingredientDao;
    private final RecipeDao recipeDao;
    private final Search search;
    private final JwtUtil jwtUtil;


    public SearchController(IngredientDao ingredientDao, RecipeDao recipeDao, Search search, JwtUtil jwtUtil) {
        this.ingredientDao = ingredientDao;
        this.recipeDao = recipeDao;
        this.search = search;
        this.jwtUtil = jwtUtil;
    }




    @PostMapping
    public ResponseEntity<Search> filterRecipe(@RequestBody List<Integer> ingredients, @RequestParam String title, @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String email;
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            email = jwtUtil.getEmailFromToken(token);
        }else {
            email = null;
        }
        if (title != "") {
            search.setIngredients(ingredientDao.findIngredientByName(title));
        }
        search.setRecipes(recipeDao.findRecipesByIngredientsAndName(ingredients, title, email));
        return ResponseEntity.ok(search);
    }
}
