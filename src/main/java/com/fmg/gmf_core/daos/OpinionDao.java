package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Favorite;
import com.fmg.gmf_core.entitys.Opinion;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.helpers.RecipeHelper;
import com.fmg.gmf_core.helpers.UserHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OpinionDao {
    private final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;
    private final UserHelper userHelper;
    private final RecipeHelper recipeHelper;

    public OpinionDao(JdbcTemplate jdbcTemplate, GlobalHelper globalHelper, UserHelper userHelper, RecipeHelper recipeHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
        this.userHelper = userHelper;
        this.recipeHelper = recipeHelper;
    }
    private final RowMapper<Opinion> opinionRowMapper = (rs,_) -> new Opinion(
            rs.getInt("id_recipe"),
            rs.getString("email"),
            rs.getInt("rate"),
            rs.getString("comment")
    );
    public List<Opinion> findRecipeOpinion(int id_recipe){
        String sql = "SELECT * FROM opinion where id_recipe = ?";
        List<Opinion> opinions = jdbcTemplate.query(sql, opinionRowMapper, id_recipe);
        return opinions;
    }
    public Opinion save(Opinion opinion) {
        userHelper.emailExist(opinion.getEmail());
        recipeHelper.recipeExist(opinion.getId_recipe());
        String sql = "INSERT INTO opinion (id_recipe, email, rate, comment) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, opinion.getId_recipe(),opinion.getEmail(),opinion.getRate(),opinion.getComment());
        return opinion;
    }
}
