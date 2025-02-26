package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Ingredient;
import com.fmg.gmf_core.entitys.RecipeDetails;
import com.fmg.gmf_core.helpers.GlobalHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeDetailsDao {
    private final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;

    public RecipeDetailsDao(JdbcTemplate jdbcTemplate, GlobalHelper globalHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
    }



}
