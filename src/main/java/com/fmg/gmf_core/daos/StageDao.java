package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.entitys.Stage;
import com.fmg.gmf_core.exceptions.ResourceAlreadyExistException;
import com.fmg.gmf_core.exceptions.ResourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StageDao {
    private  final JdbcTemplate jdbcTemplate;

    public StageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Stage> stageRowMapper = (rs, _) -> new Stage(
            rs.getInt("stage"),
            rs.getInt("id_recipe"),
            rs.getString("content")
    );
    public List<Stage> findAll() {
        String sql = "SELECT * FROM stage";
        List<Stage> stages = jdbcTemplate.query(sql, stageRowMapper);
        if (stages.isEmpty()){
            throw new ResourceNotFoundException("Aucune étape disponible");
        }
        return stages;
    }
    public List<Stage> findRecipeStage(int id){
        String sql = "SELECT * FROM stage where id_recipe = ?";
        List<Stage> stages = jdbcTemplate.query(sql, stageRowMapper, id);
        if (stages.isEmpty()){
            throw new ResourceNotFoundException("Aucune étape disponible pour la recette avec l'id"+id);
        }
        return stages;
    }
    public boolean save(Stage stage) {
        if (stageExist(stage.getId_recipe())){
            throw new ResourceAlreadyExistException("L'étape existe déjà");
        }
        String sql = "INSERT INTO stage (stage, id_recipe, content) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql,stage.getStage(), stage.getId_recipe(), stage.getContent());
        return rowsAffected >0;
    }
    private boolean stageExist(int id) {
        String checkSql = "SELECT COUNT(*) FROM stage WHERE id_recipe = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);
        return count > 0;
    }
}
