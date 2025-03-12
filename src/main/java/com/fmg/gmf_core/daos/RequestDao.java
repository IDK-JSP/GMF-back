package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.helpers.GlobalHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RequestDao {
    private final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;
    public RequestDao(JdbcTemplate jdbcTemplate, GlobalHelper globalHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
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
        String sql = "SELECT * FROM recipe where state = 'tovalidation'";
        List<Recipe> recipes = jdbcTemplate.query(sql, recipeRowMapper);
        globalHelper.isEmpty(recipes, "recette à validé");
        return recipes ;
    }

}
