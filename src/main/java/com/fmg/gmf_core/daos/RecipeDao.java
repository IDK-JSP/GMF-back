package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.helpers.UserHelper;
import com.fmg.gmf_core.services.DateTimeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class RecipeDao {
    private final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;
    private final UserHelper userHelper;
    private final DateTimeService dateTimeService;


    public RecipeDao(JdbcTemplate jdbcTemplate, UserDao userDao, GlobalHelper globalHelper, UserHelper userHelper, DateTimeService dateTimeService){
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
        this.userHelper = userHelper;
        this.dateTimeService = dateTimeService;
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
            rs.getTimestamp("create_time").toLocalDateTime(),
            rs.getTimestamp("update_time").toLocalDateTime()

    );

    public List<Recipe> findAll() {
        String sql = "SELECT * FROM recipe";
        List<Recipe> recipes = jdbcTemplate.query(sql, recipeRowMapper);
        globalHelper.isEmpty(recipes, "recette");
        return recipes ;
    }
    public List<Recipe> findRecipeByName(String research){
        String sql = "SELECT * FROM recipe WHERE title LIKE ?";
        List<Recipe> recipes = jdbcTemplate.query(sql, recipeRowMapper, "%" + research + "%");
        return recipes;
    }

    public int save(Recipe recipe) {
        globalHelper.notExist(recipeExist(recipe.getTitle()),"Recette");
        userHelper.emailExist(recipe.getEmail());
        String sql = "INSERT INTO recipe (email, title, create_time) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, recipe.getEmail(), recipe.getTitle(),dateTimeService.getCurrentDateTime());
        return findRecipeIdByName(recipe.getTitle());
    }
    public List<Recipe> findRecipesByIngredients(List<Integer> ingredientIds) {
        // Vérifie que la liste des ingrédients n'est pas vide
        if (ingredientIds == null || ingredientIds.isEmpty()) {
            throw new IllegalArgumentException("La liste des ingrédients ne peut pas être vide");
        }

        // Générer les paramètres dynamiques pour la clause IN
        String placeholders = String.join(",", Collections.nCopies(ingredientIds.size(), "?"));

        // Créer la requête SQL dynamique
        String sql = "SELECT r.id_recipe, r.email, r.title, r.content, r.image, r.person, r.state, r.rate, r.nb_rate, r.create_time, r.update_time, COUNT(ri.id_ingredient) AS matching_ingredients " +
                "FROM recipe r " +
                "JOIN recipe_ingredient ri ON r.id_recipe = ri.id_recipe " +
                "WHERE ri.id_ingredient IN (" + placeholders + ") " +
                "GROUP BY r.id_recipe " +
                "ORDER BY matching_ingredients DESC";

        // Exécuter la requête et récupérer les résultats
        List<Recipe> recipes = jdbcTemplate.query(sql, recipeRowMapper, ingredientIds.toArray());
        return recipes;
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
