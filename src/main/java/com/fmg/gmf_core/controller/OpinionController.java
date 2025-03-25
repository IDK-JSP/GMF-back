package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.OpinionDao;
import com.fmg.gmf_core.daos.RecipeDao;
import com.fmg.gmf_core.entitys.Opinion;
import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.security.JwtUtil;
import com.fmg.gmf_core.services.RatingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/opinion")
public class OpinionController {
    private final OpinionDao opinionDao;
    private final RecipeDao recipeDao;
    private final RatingService ratingService;
    private final JwtUtil jwtUtil;

    public OpinionController(OpinionDao opinionDao, RecipeDao recipeDao, RatingService ratingService, JwtUtil jwtUtil) {
        this.opinionDao = opinionDao;
        this.recipeDao = recipeDao;
        this.ratingService = ratingService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/new")
    public ResponseEntity<Opinion> newOpinion(@Valid @RequestBody Opinion opinion, @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            String email = jwtUtil.getEmailFromToken(token);
            opinion.setEmail(email);
            Opinion newOpinion = opinionDao.save(opinion);
            Recipe recipe = recipeDao.findRecipeById(opinion.getId_recipe());
            recipe.setRate(ratingService.rate(recipe.getRate(), recipe.getNbRate(), opinion.getRate()));
            recipe.setNbRate(recipe.getNbRate() + 1);
            recipeDao.updateRecipe(recipe);
            return ResponseEntity.ok(newOpinion);
        }else {
            return (ResponseEntity<Opinion>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }
}
