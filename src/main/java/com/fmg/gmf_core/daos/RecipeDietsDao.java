package com.fmg.gmf_core.daos;


import com.fmg.gmf_core.dtos.RecipeDietsDto;
import com.fmg.gmf_core.entitys.Diet;
import com.fmg.gmf_core.helpers.GlobalHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeDietsDao {
    private final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;

    public RecipeDietsDao(JdbcTemplate jdbcTemplate, GlobalHelper globalHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
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
            rs.getString("diet")
    );

    public List<RecipeDietsDto> findAll() {
        String sql = "SELECT r.*, " +
                "       CASE " +
                "           WHEN COUNT(DISTINCT d.id_diet) = 0 THEN 'Non classé' " +
                "           WHEN SUM(CASE WHEN d.name = 'Végan' THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient) " +
                "           THEN 'Végan' " +
                "           WHEN SUM(CASE WHEN d.name IN ('Végétarien') THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient) " +
                "           THEN 'Végétarien' " +
                "           ELSE 'Non végétarien' " +
                "       END AS diet " +
                " FROM recipe AS r" +
                " JOIN recipe_ingredient ri ON r.id_recipe = ri.id_recipe " +
                " JOIN ingredient i ON ri.id_ingredient = i.id_ingredient " +
                " LEFT JOIN diet_ingredient di ON ri.id_ingredient = di.id_ingredient " +
                " LEFT JOIN diet d ON di.id_diet = d.id_diet " +
                " GROUP BY r.id_recipe; ";
        List<RecipeDietsDto> recipes = jdbcTemplate.query(sql, recipeRowMapper);
        globalHelper.isEmpty(recipes, "recette");
        return recipes;
    }
//    public List<Diet> findDietsByRecipeId(int recipeId) {
//        String sql = "SELECT DISTINCT d.id_diet, d.name, d.content " +
//                "FROM diet d " +
//                "JOIN diet_ingredient di ON d.id_diet = di.id_diet " +
//                "JOIN ingredient i ON di.id_ingredient = i.id_ingredient " +
//                "JOIN recipe_ingredient ri ON i.id_ingredient = ri.id_ingredient " +
//                "WHERE ri.id_recipe = ? " +
//                "GROUP BY d.id_diet " +
//                "HAVING COUNT(DISTINCT ri.id_ingredient) = ( " +
//                "    SELECT COUNT(*) FROM recipe_ingredient WHERE id_recipe = ? " +
//                ")";
//
//        return jdbcTemplate.query(sql, (rs, rowNum) ->
//                        new Diet(rs.getInt("id_diet"), rs.getString("name"), rs.getString("content")),
//                recipeId, recipeId);
//    }
}
