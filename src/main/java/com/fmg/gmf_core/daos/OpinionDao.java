package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Opinion;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.helpers.RecipeHelper;
import com.fmg.gmf_core.helpers.UserHelper;
import com.fmg.gmf_core.services.DateTimeService;
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
    private final DateTimeService dateTimeService;

    public OpinionDao(JdbcTemplate jdbcTemplate, GlobalHelper globalHelper, UserHelper userHelper, RecipeHelper recipeHelper, DateTimeService dateTimeService) {
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
        this.userHelper = userHelper;
        this.recipeHelper = recipeHelper;
        this.dateTimeService = dateTimeService;
    }
    private final RowMapper<Opinion> opinionRowMapper = (rs,_) -> new Opinion(
            rs.getInt("id_recipe"),
            rs.getString("email"),
            rs.getInt("rate"),
            rs.getString("comment"),
            rs.getTimestamp("create_time").toLocalDateTime()
    );
    public List<Opinion> findRecipeOpinion(int id_recipe){
        recipeHelper.recipeExist(id_recipe);
        String sql = "SELECT * FROM opinion where id_recipe = ? ORDER BY create_time DESC";
        List<Opinion> opinions = jdbcTemplate.query(sql, opinionRowMapper, id_recipe);
        return opinions;
    }
    public Opinion save(Opinion opinion) {
        userHelper.emailExist(opinion.getEmail());
        recipeHelper.recipeExist(opinion.getId_recipe());
        globalHelper.notExist(opinionExist(opinion),"L'avis");
        String sql = "INSERT INTO opinion (id_recipe, email, rate, comment, create_time) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, opinion.getId_recipe(),opinion.getEmail(),opinion.getRate(),opinion.getComment(),dateTimeService.getCurrentDateTime());
        return opinion;
    }
    public void deleteOpinion(int recipeId, String email) {
        String sql = "DELETE FROM opinion WHERE id_recipe = ? AND email = ?";
        jdbcTemplate.update(sql, recipeId, email);
    }
    //Utilitaire
    public boolean opinionExist(Opinion opinion) {
        String checkSql = "SELECT COUNT(*) FROM opinion WHERE id_recipe = ? and email = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, opinion.getId_recipe(), opinion.getEmail());
        return count > 0;
    }
}
