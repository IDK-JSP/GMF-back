package com.fmg.gmf_core.daos;


import com.fmg.gmf_core.dtos.RecipeDietsDto;
import com.fmg.gmf_core.entitys.Diet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeDietsDao {
    private final JdbcTemplate jdbcTemplate;

    public RecipeDietsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<RecipeDietsDto> recipeRowMapper = (rs, rowNum) -> new RecipeDietsDto(
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
            rs.getTimestamp("update_time").toLocalDateTime(),
            new ArrayList<>()
    );

    public List<RecipeDietsDto> findAll() {
        String sql = "SELECT * FROM recipe";
        List<RecipeDietsDto> recipes = jdbcTemplate.query(sql, recipeRowMapper);

        for (RecipeDietsDto recipe : recipes) {
            List<Diet> diets = findDietsByRecipeId(recipe.getId());
            recipe.setDiets(diets);
        }

        return recipes;
    }

    public List<Diet> findDietsByRecipeId(int recipeId) {
        String sql = "SELECT DISTINCT d.id_diet, d.name, d.content " +
                "FROM diet d " +
                "JOIN diet_ingredient di ON d.id_diet = di.id_diet " +
                "JOIN ingredient i ON di.id_ingredient = i.id_ingredient " +
                "JOIN recipe_ingredient ri ON i.id_ingredient = ri.id_ingredient " +
                "WHERE ri.id_recipe = ? " +
                "GROUP BY d.id_diet " +
                "HAVING COUNT(DISTINCT ri.id_ingredient) = ( " +
                "    SELECT COUNT(*) FROM recipe_ingredient WHERE id_recipe = ? " +
                ")";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                        new Diet(rs.getInt("id_diet"), rs.getString("name"), rs.getString("content")),
                recipeId, recipeId);
    }
}
