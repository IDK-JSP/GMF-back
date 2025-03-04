package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.helpers.UserHelper;
import com.fmg.gmf_core.services.DateTimeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public List<Recipe> findRecipesByIngredientsAndName(List<Integer> ingredientIds, String search) {
        // Base de la requête SQL
        StringBuilder sql = new StringBuilder(
                "SELECT r.id_recipe, r.email, r.title, r.content, r.image, r.person, " +
                        "r.state, r.rate, r.nb_rate, r.create_time, r.update_time, " +
                        "COUNT(ri.id_ingredient) AS matching_ingredients " +
                        "FROM recipe r " +
                        "LEFT JOIN recipe_ingredient ri ON r.id_recipe = ri.id_recipe "
        );

        // Liste pour stocker les paramètres SQL
        List<Object> params = new ArrayList<>();

        // Ajout de la condition sur les ingrédients uniquement si la liste n'est pas vide
        if (ingredientIds != null && !ingredientIds.isEmpty()) {
            String placeholders = String.join(",", Collections.nCopies(ingredientIds.size(), "?"));
            sql.append("WHERE ri.id_ingredient IN (").append(placeholders).append(") ");
            params.addAll(ingredientIds);
        }

        // Ajout de la condition sur le titre (si une condition WHERE existe déjà, on utilise AND)
        if (search != null && !search.isBlank()) {
            if (params.isEmpty()) {
                sql.append("WHERE ");
            } else {
                sql.append("AND ");
            }
            sql.append("r.title LIKE ? ");
            params.add("%" + search + "%");
        }

        // Ajout du GROUP BY et ORDER BY
        sql.append("GROUP BY r.id_recipe ORDER BY matching_ingredients DESC");

        return jdbcTemplate.query(sql.toString(), recipeRowMapper, params.toArray());
    }

    public Recipe findRecipeById (int id){
        String sql = "SELECT * from recipe where id_recipe = ?";
        return jdbcTemplate.queryForObject(sql, recipeRowMapper, id);
    }
    public Recipe updateRecipe (Recipe recipe){
        String sql = "UPDATE recipe set email = ?, title = ?, content = ?, image = ?, person = ?, state = ?, rate = ?, nb_rate = ? where id_recipe = ?;";
        jdbcTemplate.update(sql, recipe.getEmail(), recipe.getTitle(), recipe.getContent(), recipe.getImage(), recipe.getPerson(), recipe.getState(), recipe.getRate(), recipe.getNb_rate(), recipe.getId_recipe());
        return recipe;
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
