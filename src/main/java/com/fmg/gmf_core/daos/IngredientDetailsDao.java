package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.dtos.IngredientDetailsDto;
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
    private final RowMapper<IngredientDetailsDto> recipeDetailsRowMapper = (rs, _) -> new IngredientDetailsDto(
            rs.getString("ingredient_name"),
            rs.getInt("quantity"),
            rs.getString("measurement"),
            rs.getString("diet")
    );

    public List<IngredientDetailsDto> findRecipeIngredients(int idRecipe) {
        recipeHelper.recipeExist(idRecipe);
        String sql = """
                SELECT\s
                    i.name AS ingredient_name,\s
                    ri.quantity,\s
                    m.name AS measurement,\s
                    COALESCE(
                        CASE\s
                            WHEN GROUP_CONCAT(DISTINCT d.name ORDER BY d.name SEPARATOR ', ') LIKE '%Végan%' THEN 'Végan'\s
                            WHEN GROUP_CONCAT(DISTINCT d.name ORDER BY d.name SEPARATOR ', ') IS NOT NULL THEN GROUP_CONCAT(DISTINCT d.name ORDER BY d.name SEPARATOR ', ')
                            ELSE 'Non renseigné'\s
                        END, 'Non renseigné'
                    ) AS diet\s
                FROM recipe_ingredient ri \s
                JOIN ingredient i ON ri.id_ingredient = i.id_ingredient \s
                JOIN recipe r ON ri.id_recipe = r.id_recipe \s
                JOIN measurement m ON ri.id_measurement = m.id_measurement \s
                LEFT JOIN diet_ingredient di ON ri.id_ingredient = di.id_ingredient\s
                LEFT JOIN diet d ON di.id_diet = d.id_diet \s
                WHERE r.id_recipe = ?\s
                GROUP BY i.name, ri.quantity, m.name;
                
                """;
        List<IngredientDetailsDto> ingredientDetailDtos = jdbcTemplate.query(sql, recipeDetailsRowMapper, idRecipe);
        return ingredientDetailDtos;
    }


}
