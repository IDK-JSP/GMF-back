package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.IngredientDao;
import com.fmg.gmf_core.entitys.Ingredient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientDao ingredientDao;

    public IngredientController(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Ingredient>> getAllIngredient() {
        return ResponseEntity.ok(ingredientDao.findAll());
    }
    /*@GetMapping("/search")
    public List<Ingredient> searchIngredient(@RequestParam String name) {
        return ingredientDao.findIngredientByName(name);
    }*/
    @PostMapping("/new")
    public ResponseEntity<Ingredient> newIngredient(@RequestBody Ingredient ingredient, @RequestHeader("Authorization") String authorizationHeader){
        ingredientDao.save(ingredient);
        return ResponseEntity.ok(ingredient);
    }
}
