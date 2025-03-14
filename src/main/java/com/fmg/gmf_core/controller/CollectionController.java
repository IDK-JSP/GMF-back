package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.SearchDao;
import com.fmg.gmf_core.dtos.RecipeDietsDto;
import com.fmg.gmf_core.security.JwtUtil;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<RecipeDietsDto>> topRecipe(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String email;
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            email = jwtUtil.getEmailFromToken(token);
        }else {
            email = null;
        }
        return ResponseEntity.ok(searchDao.orderBy("rate", email));
    }

    @GetMapping("/nbRate")
    public ResponseEntity<List<RecipeDietsDto>> mostRated(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String email;
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            email = jwtUtil.getEmailFromToken(token);
        }else {
            email = null;
        }
        return ResponseEntity.ok(searchDao.orderBy("nb_rate", email));
    }

    @GetMapping("/recent")
    public ResponseEntity<List<RecipeDietsDto>> recentRecipeDone(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String email;
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            email = jwtUtil.getEmailFromToken(token);
        }else {
            email = null;
        }
        return ResponseEntity.ok(searchDao.orderBy("MAX(o.create_time)", email));
    }

    @GetMapping("/vege")
    public ResponseEntity<List<RecipeDietsDto>> vegeRecipe(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String email;
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            email = jwtUtil.getEmailFromToken(token);
        }else {
            email = null;
        }
        return ResponseEntity.ok(searchDao.having("'Végétarien'", email));
    }

    @GetMapping("/vegan")
    public ResponseEntity<List<RecipeDietsDto>> veganRecipe(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String email;
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            email = jwtUtil.getEmailFromToken(token);
        }else {
            email = null;
        }return ResponseEntity.ok(searchDao.having("'Végan'", email));
    }

    @GetMapping("/validate")
    public ResponseEntity<List<RecipeDietsDto>> validateRecipe(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String email;
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
            email = jwtUtil.getEmailFromToken(token);
        }else {
            email = null;
        }return ResponseEntity.ok(searchDao.where("r.state = 'validate'", email));
    }
}
