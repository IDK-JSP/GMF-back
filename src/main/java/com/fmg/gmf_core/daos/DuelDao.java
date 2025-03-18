package com.fmg.gmf_core.daos;

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
    private final RowMapper<Duel> duelRowMapper = (rs,_) -> new Duel(
            rs.getInt("id_duel"),
            rs.getInt("id_recipe_right"),
            rs.getInt("id_recipe_left"),
            rs.getString("email"),
            rs.getTimestamp("create_time").toLocalDateTime()
    );
    public List<Duel> findAllDuel(){
        String sql = "SELECT * FROM duel";
        List<Duel> duels = jdbcTemplate.query(sql,duelRowMapper);
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
}
