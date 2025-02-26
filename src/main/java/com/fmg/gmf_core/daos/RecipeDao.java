package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.exceptions.ResourceNotFoundException;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.helpers.UserHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeDao {
    private final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;
    private final UserHelper userHelper;


    public RecipeDao(JdbcTemplate jdbcTemplate, UserDao userDao, GlobalHelper globalHelper, UserHelper userHelper){
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
        this.userHelper = userHelper;
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
        globalHelper.isEmpty(recipes);
        return recipes ;
    }
    public int save(Recipe recipe) {
        globalHelper.notExist(recipeExist(recipe.getTitle()),"Recette");
        userHelper.emailExist(recipe.getEmail());
        String sql = "INSERT INTO recipe (email, title) VALUES (?, ?)";
        jdbcTemplate.update(sql, recipe.getEmail(), recipe.getTitle());
        return findRecipeIdByName(recipe.getTitle());
    }


    //Utilitaires
    public boolean recipeExist(String title) {
        String checkSql = "SELECT COUNT(*) FROM recipe WHERE title = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, title);
        return count > 0;
    }
    public boolean recipeIdExist(int id) {
        String checkSql = "SELECT COUNT(*) FROM recipe WHERE id_recipe = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);
        return count > 0;
    }
    private int findRecipeIdByName(String title){
        String sql = "SELECT id_recipe FROM recipe where title = ?";
        int id_recipe = jdbcTemplate.queryForObject(sql,Integer.class, title);
        return id_recipe;
    }
}
