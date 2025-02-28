package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.IngredientDetails;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.helpers.RecipeHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class IngredientDetailsDao {
    private final JdbcTemplate jdbcTemplate;
    private final RecipeHelper recipeHelper;
    private final GlobalHelper globalHelper;

    public IngredientDetailsDao(JdbcTemplate jdbcTemplate, RecipeHelper recipeHelper, GlobalHelper globalHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.recipeHelper = recipeHelper;
        this.globalHelper = globalHelper;
    }
    private final RowMapper<IngredientDetails> recipeDetailsRowMapper = (rs, _) -> new IngredientDetails(
            rs.getString("ingredient_name"),
            rs.getInt("quantity"),
            rs.getString("measurement"),
            rs.getString("diet")
    );

    public List<IngredientDetails> findRecipeIngredients(int id_recipe) {
        recipeHelper.recipeExist(id_recipe);
        String sql = "SELECT i.name AS ingredient_name, ri.quantity, m.name AS measurement, CASE WHEN GROUP_CONCAT(DISTINCT d.name ORDER BY d.name SEPARATOR ', ') LIKE '%Végan%' THEN 'Végan' ELSE GROUP_CONCAT(DISTINCT d.name ORDER BY d.name SEPARATOR ', ') END AS diet FROM recipe_ingredient ri  JOIN ingredient i ON ri.id_ingredient = i.id_ingredient  JOIN recipe r ON ri.id_recipe = r.id_recipe  JOIN measurement m ON ri.id_measurement = m.id_measurement  JOIN diet_ingredient di ON ri.id_ingredient = di.id_ingredient JOIN diet d ON di.id_diet = d.id_diet WHERE r.id_recipe = ? GROUP BY i.name, ri.quantity, m.name;";
        List<IngredientDetails> ingredientDetails = jdbcTemplate.query(sql, recipeDetailsRowMapper, id_recipe);
        globalHelper.isEmpty(ingredientDetails, "détails d'ingrédient");
        return ingredientDetails;
    }


}
