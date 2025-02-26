package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.User;
import com.fmg.gmf_core.exceptions.ResourceAlreadyExistException;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.helpers.UserHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public class UserDao {
    private  final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;

    public UserDao(JdbcTemplate jdbcTemplate, GlobalHelper globalHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
    }
    private final RowMapper<User> userRowMapper = (rs, rowNum) -> new User(
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("role"),
            rs.getString("pseudo"),
            rs.getString("image"),
            rs.getTimestamp("create") != null ? rs.getTimestamp("create").toLocalDateTime() : null,
            rs.getTimestamp("update") != null ? rs.getTimestamp("update").toLocalDateTime() : null
    );



    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        List<User> users = jdbcTemplate.query(sql, userRowMapper);
        globalHelper.isEmpty(users);
        return users;
    }
    public User findByEmail(String email) {
        String sql = "SELECT * FROM `user` WHERE email = ?";
        globalHelper.notExist(!emailExists(email),"Email");
        return jdbcTemplate.queryForObject(sql, userRowMapper, email);

    }

    public boolean save(User user) {
        //Verifie si l'email n'existe pas déjà en base
        globalHelper.exist(emailExists(user.getEmail()),"Email");
        String sql = "INSERT INTO user (email, password, pseudo) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getPseudo());
        return rowsAffected >0;
    }



    //Utilitaire
    public boolean emailExists(String email) {
        String checkSql = "SELECT COUNT(*) FROM user WHERE email = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, email);
        return count > 0;
    }



}
