package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.User;
import com.fmg.gmf_core.exceptions.ResourceAlreadyExistException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public class UserDao {
    private  final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        return jdbcTemplate.query(sql, userRowMapper);
    }
    public User findByEmail(String email) {
        String sql = "SELECT * FROM `user` WHERE email = ?";
        return jdbcTemplate.query(sql, userRowMapper, email)
                .stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
    }

    public boolean save(User user) {
        if (emailExists(user.getEmail())){
            throw new ResourceAlreadyExistException("Email :"+user.getEmail()+" already registerd");
        }
        String sql = "INSERT INTO user (email, password, pseudo) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getPseudo());
        return rowsAffected >0;
    }


    private boolean emailExists(String email) {
        String checkSql = "SELECT COUNT(*) FROM user WHERE email = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, email);
        return count > 0;
    }



}
