package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.IngredientDetails;
import com.fmg.gmf_core.helpers.GlobalHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class IngredientDetailsDao {
    private final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;

    public IngredientDetailsDao(JdbcTemplate jdbcTemplate, GlobalHelper globalHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
    }
    private final RowMapper<IngredientDetails> recipeDetailsRowMapper = (rs, _) -> new IngredientDetails(
            rs.getString("ingredient_name"),
            rs.getInt("quantity"),
            rs.getString("measurement")
    );

    public List<IngredientDetails> findRecipeIngredients(int id_recipe) {
        String sql = "SELECT  i.name as ingredient_name, ri.quantity, m.name as measurement FROM recipe_ingredient ri JOIN ingredient i ON ri.id_ingredient = i.id_ingredient join recipe r on ri.id_recipe = r.id_recipe join measurement m on ri.id_measurement = m.id_measurement where r.id_recipe = ?";
        List<IngredientDetails> ingredientDetails = jdbcTemplate.query(sql, recipeDetailsRowMapper, id_recipe);
        return ingredientDetails;
    }


}
