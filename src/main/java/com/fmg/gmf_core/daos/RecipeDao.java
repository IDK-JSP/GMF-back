package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.entitys.User;
import com.fmg.gmf_core.exceptions.ResourceAlreadyExistException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeDao {
    private final JdbcTemplate jdbcTemplate;

    public RecipeDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Recipe> recipeRowMapper =(rs, _)-> new Recipe (
            rs.getInt("id_recipe"),
            rs.getString("email"),
            rs.getString("title"),
            rs.getString("content"),
            rs.getString("image"),
            rs.getInt("person"),
            rs.getString("state"),
            rs.getDouble("rate"),
            rs.getInt("nb_rate"),
            rs.getTimestamp("create") != null ? rs.getTimestamp("create").toLocalDateTime() : null,
            rs.getTimestamp("update") != null ? rs.getTimestamp("update").toLocalDateTime() : null

    );
    public List<Recipe> findAll() {
        String sql = "SELECT * FROM recipe";
        return jdbcTemplate.query(sql, recipeRowMapper);
    }
    public boolean save(Recipe recipe) {
        String sql = "INSERT INTO recipe (id_recipe, email, title) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, recipe.getId_recipe(), recipe.getEmail(), recipe.getTitle());
        return rowsAffected >0;
    }
}
