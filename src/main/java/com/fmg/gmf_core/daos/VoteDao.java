package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Vote;
import com.fmg.gmf_core.helpers.DuelHelper;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.helpers.RecipeHelper;
import com.fmg.gmf_core.helpers.UserHelper;
import com.fmg.gmf_core.services.DateTimeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoteDao {
    private final JdbcTemplate jdbcTemplate;
    private final DuelHelper duelHelper;
    private final GlobalHelper globalHelper;
    private final UserHelper userHelper;
    private final RecipeHelper recipeHelper;
    private final DateTimeService dateTimeService;
    public VoteDao(JdbcTemplate jdbcTemplate, DuelHelper duelHelper, GlobalHelper globalHelper, UserHelper userHelper, RecipeHelper recipeHelper, DateTimeService dateTimeService) {
        this.jdbcTemplate = jdbcTemplate;
        this.duelHelper = duelHelper;
        this.globalHelper = globalHelper;
        this.userHelper = userHelper;
        this.recipeHelper = recipeHelper;
        this.dateTimeService = dateTimeService;
    }
    private final RowMapper<Vote> voteRowMapper = (rs,_) -> new Vote(
            rs.getInt("id_duel"),
            rs.getString("email"),
            rs.getInt("id_duel"),
            rs.getTimestamp("create_time").toLocalDateTime()
    );
    public List<Vote> getAllDuelVote(int idDuel){
        duelHelper.duelExist(idDuel);
        String sql = "SELECT * FROM vote where idDuel = ? ";
        List<Vote> votes = jdbcTemplate.query(sql,voteRowMapper, idDuel);
        globalHelper.isEmpty(votes, "vote pour ce duel");
        return votes;
    }
    public Vote save(Vote vote){
        globalHelper.notExist(voteExist(vote.getEmail(),vote.getId_duel()), "Vote pour ce duel");
        duelHelper.duelExist(vote.getId_duel());
        recipeHelper.recipeExist(vote.getId_recipe());
        userHelper.emailExist(vote.getEmail());
        duelHelper.recipeExistInDuel(vote.getId_duel(),vote.getId_recipe());
        String sql = "INSERT INTO vote (id_duel, email, id_recipe, create_time) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,vote.getId_duel(),vote.getEmail(),vote.getId_recipe(),dateTimeService.getCurrentDateTime());
        return vote;
    }
    public int totalDuelVote(int idDuel){
        duelHelper.duelExist(idDuel);
        String sql = "SELECT COUNT(*) FROM vote WHERE id_duel = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class, idDuel);
    }
    public int totalDuelRightVote(int idDuel){
        duelHelper.duelExist(idDuel);
        String sql = "SELECT COUNT(*) FROM duel WHERE id_duel = ? and id_recipe_right = ? ";
        return jdbcTemplate.queryForObject(sql,Integer.class, idDuel,duelRecipeRightId(idDuel));
    }
    //Utiliraire
    public boolean voteExist(String email, int idDuel){
        String sql = "SELECT COUNT(*) FROM vote WHERE email = ? and id_duel = ?";
        int count = jdbcTemplate.queryForObject(sql,Integer.class, email, idDuel);
        return count > 0;
    }
    public int duelRecipeRightId (int idDuel){
        String sql = "SELECT id_recipe_right FROM duel WHERE id_duel = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class, idDuel);
    }
}
