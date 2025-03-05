package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.OpinionDao;
import com.fmg.gmf_core.daos.RecipeDao;
import com.fmg.gmf_core.entitys.Favorite;
import com.fmg.gmf_core.entitys.Opinion;
import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/opinion")
public class OpinionController {
    private final OpinionDao opinionDao;
    private final RecipeDao recipeDao;
    private final RatingService ratingService;

    public OpinionController(OpinionDao opinionDao, RecipeDao recipeDao, RatingService ratingService) {
        this.opinionDao = opinionDao;
        this.recipeDao = recipeDao;
        this.ratingService = ratingService;
    }

    @PostMapping("/new")
    public ResponseEntity<Opinion> newOpinion(@RequestBody Opinion opinion, @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            Opinion newOpinion = opinionDao.save(opinion);
            Recipe recipe = recipeDao.findRecipeById(opinion.getId_recipe());
            recipe.setRate(ratingService.rate(recipe.getRate(), recipe.getNb_rate(), opinion.getRate()));
            recipe.setNb_rate(recipe.getNb_rate() + 1);
            recipeDao.updateRecipe(recipe);
            return ResponseEntity.ok(opinion);
        }else {
            return (ResponseEntity<Opinion>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }
}
