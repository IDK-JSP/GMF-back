package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.dtos.DuelDto;
import com.fmg.gmf_core.entitys.Duel;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.helpers.RecipeHelper;
import com.fmg.gmf_core.helpers.UserHelper;
import com.fmg.gmf_core.services.DateTimeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.DateTimeException;
import java.util.List;
@Repository
public class DuelDao {
    private final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;
    private final UserHelper userHelper;
    private final RecipeHelper recipeHelper;
    private final DateTimeService dateTimeService;
    public DuelDao(JdbcTemplate jdbcTemplate, GlobalHelper globalHelper, UserHelper userHelper, RecipeHelper recipeHelper, DateTimeService dateTimeService) {
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
        this.userHelper = userHelper;
        this.recipeHelper = recipeHelper;
        this.dateTimeService = dateTimeService;
    }
    private final RowMapper<DuelDto> duelDtoRowMapper = (rs,_) -> new DuelDto(
            rs.getInt("id_duel"),
            rs.getString("email"),
            rs.getInt("id_recipe_left"),
            rs.getInt("id_recipe_right"),
            rs.getTimestamp("create_time").toLocalDateTime(),
            rs.getInt("total_vote"),
            rs.getInt("total_votes_right")
    );
    private final RowMapper<Duel>duelRowMapper = (rs,_)-> new Duel(
            rs.getInt("id_duel"),
            rs.getInt("id_recipe_right"),
            rs.getInt("id_recipe_left"),
            rs.getString("email"),
            rs.getTimestamp("create_time").toLocalDateTime()
    );
    public List<DuelDto> findAllDuel(){
        String sql = """
                SELECT d.id_duel,d.email,d.id_recipe_left,d.id_recipe_right,d.create_time,count(*)as total_vote,
                COUNT(CASE WHEN v.id_recipe = d.id_recipe_right THEN 1 END) AS total_votes_right
                FROM vote as v\s
                join duel d on v.id_duel = d.id_duel
                GROUP BY d.id_duel, d.email, d.id_recipe_left, d.id_recipe_right, d.create_time;
                """;
        List<DuelDto> duels = jdbcTemplate.query(sql,duelDtoRowMapper);
        globalHelper.isEmpty(duels,"duel");
        return duels;
    }
    public Duel save(Duel duel){
        userHelper.emailExist(duel.getEmail());
        recipeHelper.recipeExist(duel.getId_recipe_left());
        recipeHelper.recipeExist(duel.getId_recipe_right());
        String sql = "INSERT INTO duel (id_recipe_right, id_recipe_left, email, create_time) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,duel.getId_recipe_right(),duel.getId_recipe_left(),duel.getEmail(),dateTimeService.getCurrentDateTime());
        return findDuel(duel.getEmail(), duel.getId_recipe_left(), duel.getId_recipe_right()).getFirst();
    }
    public Duel findDuelById(int id_duel){
        String sql = "SELECT * FROM duel WHERE id_duel = ?";
        return jdbcTemplate.queryForObject(sql,duelRowMapper,id_duel);
    }
    public List<Duel> findRecipeDuel(int id_recipe){
        String sql = "SELECT * FROM duel WHERE id_recipe_left = ? or id_recipe_right = ?";
        return jdbcTemplate.query(sql,duelRowMapper,id_recipe, id_recipe);
    }
    public void deletedDuel(int recipeId) {
        String sql = "DELETE FROM duel WHERE id_recipe_left = ? or id_recipe_right = ?";
        jdbcTemplate.update(sql, recipeId, recipeId);
    }
    //Utilitaire
    public List<Duel> findDuel(String email, int recipeLeft, int recipeRight){
        String sql = "SELECT * FROM duel WHERE email = ? and id_recipe_left = ? and id_recipe_right = ? order by create_time DESC";
        return jdbcTemplate.query(sql,duelRowMapper,email,recipeLeft,recipeRight);
    }
    public boolean duelExist (int id_duel){
        String sql = "SELECT count(*) from duel where id_duel = ?" ;
        int count = jdbcTemplate.queryForObject(sql,Integer.class,id_duel);
        return count > 0;
    }
    public boolean recipeExistInDuel (int id_duel, int id_recipe){
        String sql = "SELECT count(*) from duel where id_duel = ? and id_recipe_right = ? or id_recipe_left = ?";
        int count = jdbcTemplate.queryForObject(sql,Integer.class,id_duel, id_recipe, id_recipe);
        return count >0;
    }
}
