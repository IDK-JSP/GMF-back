package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.entitys.User;
import com.fmg.gmf_core.exceptions.ResourceAlreadyExistException;
import com.fmg.gmf_core.exceptions.ResourceNotFoundException;
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
        List<Recipe> recipes = jdbcTemplate.query(sql, recipeRowMapper);
        if (recipes.isEmpty()){
            throw new ResourceNotFoundException("Aucune recette disponible");
        }
        return recipes ;
    }
    public int findRecipeIdByName(String title){
        String sql = "SELECT id_recipe FROM recipe where title = ?";
        int id_recipe = jdbcTemplate.update(sql, title);
        return id_recipe;
    }
    public int save(Recipe recipe) {
        if (recipeExist(recipe.getTitle())){
            throw new ResourceAlreadyExistException("La recette" + recipe.getTitle()+" existe déjà");
        }
        String sql = "INSERT INTO recipe ( email, title) VALUES ( ?, ?)";
        jdbcTemplate.update(sql, recipe.getId_recipe(), recipe.getEmail(), recipe.getTitle());
        return findRecipeIdByName(recipe.getTitle());
    }
    private boolean recipeExist(String title) {
        String checkSql = "SELECT COUNT(*) FROM recipe WHERE title = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, title);
        return count > 0;
    }
}
