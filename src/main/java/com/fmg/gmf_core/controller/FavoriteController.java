package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.FavoriteDao;
import com.fmg.gmf_core.entitys.Favorite;
import com.fmg.gmf_core.entitys.RecipeDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteDao favoriteDao;
    private final Favorite favorite;

    public FavoriteController(FavoriteDao favoriteDao, Favorite favorite) {
        this.favoriteDao = favoriteDao;
        this.favorite = favorite;
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<RecipeDetails> getRecipeDetails(@PathVariable int id){
        favoriteDao
        return  ResponseEntity.ok(recipeDetails);
    }*/
}
