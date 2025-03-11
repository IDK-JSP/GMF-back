package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.SearchDao;
import com.fmg.gmf_core.dtos.RecipeDietsDto;
import com.fmg.gmf_core.security.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {
    private final SearchDao searchDao;
    private final JwtUtil jwtUtil;

    public CollectionController(SearchDao searchDao, JwtUtil jwtUtil) {
        this.searchDao = searchDao;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/top")
    public List<RecipeDietsDto> topRecipe(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String email;
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            email = jwtUtil.getEmailFromToken(token);
        }else {
            email = null;
        }
        return searchDao.orderBy("rate", email);
    }

    @GetMapping("/nbRate")
    public List<RecipeDietsDto> mostRated(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String email;
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            email = jwtUtil.getEmailFromToken(token);
        }else {
            email = null;
        }
        return searchDao.orderBy("nb_rate", email);
    }

    @GetMapping("/recent")
    public List<RecipeDietsDto> recentRecipeDone(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String email;
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            email = jwtUtil.getEmailFromToken(token);
        }else {
            email = null;
        }
        return searchDao.orderBy("MAX(o.create_time)", email);
    }

    @GetMapping("/vege")
    public List<RecipeDietsDto> vegeRecipe(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String email;
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            email = jwtUtil.getEmailFromToken(token);
        }else {
            email = null;
        }
        return searchDao.having("'Végétarien'", email);
    }

    @GetMapping("/vegan")
    public List<RecipeDietsDto> veganRecipe(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String email;
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            email = jwtUtil.getEmailFromToken(token);
        }else {
            email = null;
        }return searchDao.having("'Végan'", email);
    }

    @GetMapping("/validate")
    public List<RecipeDietsDto> validateRecipe(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String email;
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            email = jwtUtil.getEmailFromToken(token);
        }else {
            email = null;
        }return searchDao.where("r.state = 'validate'", email);
    }
}
