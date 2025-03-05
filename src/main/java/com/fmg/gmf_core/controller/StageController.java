package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.RecipeDao;
import com.fmg.gmf_core.daos.StageDao;
import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.entitys.Stage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stage")
public class StageController {
    private final StageDao stageDao;
    public StageController(StageDao stageDao){
        this.stageDao =stageDao;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Stage>> getAllStage(){
        return ResponseEntity.ok(stageDao.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Stage>> getAllStageRecipe(@PathVariable int id){
        return ResponseEntity.ok(stageDao.findRecipeStage(id));
    }
}
