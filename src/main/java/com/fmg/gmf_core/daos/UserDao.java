package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.User;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.services.DateTimeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public class UserDao {
    private  final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;
    private final DateTimeService dateTimeService;

    public UserDao(JdbcTemplate jdbcTemplate, GlobalHelper globalHelper, DateTimeService dateTimeService) {
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
        this.dateTimeService = dateTimeService;
    }
    private final RowMapper<User> userRowMapper = (rs, rowNum) -> new User(
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("role"),
            rs.getString("pseudo"),
            rs.getString("image"),
            rs.getTimestamp("create_time").toLocalDateTime(),
            rs.getTimestamp("update_time").toLocalDateTime()
    );



    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        List<User> users = jdbcTemplate.query(sql, userRowMapper);
        globalHelper.isEmpty(users, "utilisateur");
        return users;
    }
    public User findByEmail(String email) {
        globalHelper.notExist(!emailExists(email),"Email");
        String sql = "SELECT * FROM `user` WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, email);

    }

    public boolean save(User user) {
        //Verifie si l'email n'existe pas déjà en base
        globalHelper.notExist(emailExists(user.getEmail()),"Email");
        String sql = "INSERT INTO user (email, password, pseudo, create_time) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getPseudo(), dateTimeService.getCurrentDateTime());
        return rowsAffected >0;
    }



    //Utilitaire
    public boolean emailExists(String email) {
        String checkSql = "SELECT COUNT(*) FROM user WHERE email = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, email);
        return count > 0;
    }



}
