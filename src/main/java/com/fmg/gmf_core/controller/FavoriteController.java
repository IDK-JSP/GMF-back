package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.FavoriteDao;
import com.fmg.gmf_core.dtos.UserFavoriteDto;
import com.fmg.gmf_core.entitys.Favorite;
import com.fmg.gmf_core.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteDao favoriteDao;
    private final UserFavoriteDto userFavoriteDto;
    private final JwtUtil jwtUtil;

    public FavoriteController(FavoriteDao favoriteDao, UserFavoriteDto userFavoriteDto, JwtUtil jwtUtil) {
        this.favoriteDao = favoriteDao;
        this.userFavoriteDto = userFavoriteDto;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/new")
    public ResponseEntity<Favorite> newFavorite (@RequestBody Favorite favorite, @RequestHeader("Authorization") String authorizationHeader){
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            String email = jwtUtil.getEmailFromToken(token);  // Appel de votre méthode getEmailFromToken
            System.out.println("Email extrait du token : " + email);
            favorite.setEmail(email);
            favoriteDao.save(favorite);
            return ResponseEntity.ok(favorite);
        } else {
            return (ResponseEntity<Favorite>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public UserFavoriteDto searchFavorite(@RequestParam String email) {
       userFavoriteDto.setIngredients(favoriteDao.findUserAllFavoriteIngredient(email));
       userFavoriteDto.setRecipes(favoriteDao.findUserAllFavoriteRecipe(email));
       return userFavoriteDto;
    }
}
