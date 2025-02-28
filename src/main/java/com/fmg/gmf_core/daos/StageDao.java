package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Stage;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.helpers.RecipeHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StageDao {
    private  final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;
    private final RecipeHelper recipeHelper;

    public StageDao(JdbcTemplate jdbcTemplate, GlobalHelper globalHelper, RecipeHelper recipeHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
        this.recipeHelper = recipeHelper;
    }
    private final RowMapper<Stage> stageRowMapper = (rs, _) -> new Stage(
            rs.getInt("stage"),
            rs.getInt("id_recipe"),
            rs.getString("content")
    );
    public List<Stage> findAll() {
        String sql = "SELECT * FROM stage";
        List<Stage> stages = jdbcTemplate.query(sql, stageRowMapper);
        globalHelper.isEmpty(stages, "étape");
        return stages;
    }
    public List<Stage> findRecipeStage(int id){
        recipeHelper.recipeExist(id);
        String sql = "SELECT * FROM stage where id_recipe = ?";
        List<Stage> stages = jdbcTemplate.query(sql, stageRowMapper, id);
        globalHelper.isEmpty(stages, "étape");
        return stages;
    }
    public boolean save(Stage stage) {
        globalHelper.notExist(stageExist(stage.getId_recipe(),stage.getStage()),"Etape");
        recipeHelper.recipeExist(stage.getId_recipe());
        String sql = "INSERT INTO stage (stage, id_recipe, content) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql,stage.getStage(), stage.getId_recipe(), stage.getContent());
        return rowsAffected >0;
    }

    //Utilitaire
    private boolean stageExist(int id, int stage) {
        String checkSql = "SELECT COUNT(*) FROM stage WHERE id_recipe = ? and stage = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id, stage);
        return count > 0;
    }
}
