package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.FavoriteDao;
import com.fmg.gmf_core.dtos.UserFavoriteDto;
import com.fmg.gmf_core.entitys.Favorite;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteDao favoriteDao;
    private final UserFavoriteDto userFavoriteDto;

    public FavoriteController(FavoriteDao favoriteDao, UserFavoriteDto userFavoriteDto) {
        this.favoriteDao = favoriteDao;
        this.userFavoriteDto = userFavoriteDto;
    }
    @PostMapping("/new")
    public Favorite newFavorite (@RequestBody Favorite favorite){
        return favoriteDao.save(favorite);
    }

    @GetMapping("/search")
    public UserFavoriteDto searchFavorite(@RequestParam String email) {
       userFavoriteDto.setIngredients(favoriteDao.findUserAllFavoriteIngredient(email));
       userFavoriteDto.setRecipes(favoriteDao.findUserAllFavoriteRecipe(email));
       return userFavoriteDto;
    }
}
