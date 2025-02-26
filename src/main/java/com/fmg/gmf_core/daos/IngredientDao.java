package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Ingredient;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.services.DateTimeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngredientDao {
    private final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;
    private final DateTimeService dateTimeService;

    public IngredientDao(JdbcTemplate jdbcTemplate, GlobalHelper globalHelper, DateTimeService dateTimeService) {
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
        this.dateTimeService = dateTimeService;
    }
    private final RowMapper<Ingredient> ingredientRowMapper = (rs, _) -> new Ingredient(
            rs.getInt("id_ingredient"),
            rs.getString("name"),
            rs.getString("content"),
            rs.getTimestamp("create_time").toLocalDateTime(),
            rs.getTimestamp("update_time").toLocalDateTime()
    );
    public List<Ingredient> findAll() {
        String sql = "SELECT * FROM ingredient";
        List<Ingredient> ingredients = jdbcTemplate.query(sql, ingredientRowMapper);
        globalHelper.isEmpty(ingredients, "ingredient");
        return ingredients;
    }
    public Ingredient save(Ingredient ingredient) {
        globalHelper.notExist(ingredientExist(ingredient.getName()),"Recette");
        String sql = "INSERT INTO ingredient (name, create_time) VALUES (?, ?)";
        jdbcTemplate.update(sql, ingredient.getName(), dateTimeService.getCurrentDateTime());
        return ingredient;
    }

    //Utilitaire
    public Boolean ingredientExist(String name){
        String checkSql = "SELECT COUNT(*) FROM ingredient WHERE name = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, name);
        return count > 0;
    }
}
