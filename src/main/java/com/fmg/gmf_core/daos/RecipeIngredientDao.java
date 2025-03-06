package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.RecipeIngredient;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.helpers.IngredientHelper;
import com.fmg.gmf_core.helpers.MeasurementHelper;
import com.fmg.gmf_core.helpers.RecipeHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeIngredientDao {
    private final JdbcTemplate jdbcTemplate;
    private final RecipeHelper recipeHelper;
    private final IngredientHelper ingredientHelper;
    private final MeasurementHelper measurementHelper;
    private final GlobalHelper globalHelper;
    public RecipeIngredientDao(JdbcTemplate jdbcTemplate, RecipeHelper recipeHelper, IngredientHelper ingredientHelper, MeasurementHelper measurementHelper, GlobalHelper globalHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.recipeHelper = recipeHelper;
        this.ingredientHelper = ingredientHelper;
        this.measurementHelper = measurementHelper;
        this.globalHelper = globalHelper;
    }
    private final RowMapper<RecipeIngredient> recipeIngredientRowMapper = (rs,_)-> new RecipeIngredient(
            rs.getInt("id_recipe"),
            rs.getInt("id_ingredient"),
            rs.getDouble("quantity"),
            rs.getInt("id_measurement")
    );
    public RecipeIngredient save(RecipeIngredient recipeIngredient){
        globalHelper.notExist(recipeIngredientExist(recipeIngredient.getId_recipe(),recipeIngredient.getId_ingredient()),"Ingrédient de recette");
        recipeHelper.recipeExist(recipeIngredient.getId_recipe());
        ingredientHelper.ingredientExist(recipeIngredient.getId_ingredient());
        measurementHelper.measurementExist(recipeIngredient.getId_measurement());
        String sql = "INSERT INTO recipe_ingredient (id_recipe, id_ingredient, quantity, id_measurement) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, recipeIngredient.getId_recipe(), recipeIngredient.getId_ingredient(), recipeIngredient.getQuantity(),recipeIngredient.getId_measurement());
        return  recipeIngredient;
    }
    //Utilitaire
    public Boolean recipeIngredientExist(int id_recipe, int id_ingredient){
        recipeHelper.recipeExist(id_recipe);
        ingredientHelper.ingredientExist(id_ingredient);
        String sql = "SELECT COUNT(*) FROM recipe_ingredient WHERE id_recipe = ? and id_ingredient = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id_recipe, id_ingredient);
        return count > 0;
    }
}
