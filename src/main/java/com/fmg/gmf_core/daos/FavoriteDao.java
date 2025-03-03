package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Favorite;
import com.fmg.gmf_core.entitys.FavoriteIngredient;
import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.helpers.UserHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class FavoriteDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserHelper userHelper;
    private final GlobalHelper globalHelper;

    public FavoriteDao(JdbcTemplate jdbcTemplate, UserHelper userHelper, GlobalHelper globalHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userHelper = userHelper;
        this.globalHelper = globalHelper;
    }
    private final RowMapper<FavoriteIngredient> favoriteIngredientRowMapper = (rs,_)-> new FavoriteIngredient(
            rs.getInt("id_ingredient"),
            rs.getString("name"),
            rs.getString("content")
    );
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

    public List<FavoriteIngredient> findUserAllFavoriteIngredient(String email){
        userHelper.emailExist(email);
        String sql = "Select i.id_ingredient, i.name, i.content from favorite as f" +
                "join ingredient i on f.favoriteable_id = i.id_ingredient" +
                "where f.favoriteable_type = 'ingredient' " +
                "and f.favoriteable_id = ?;";
        return  jdbcTemplate.query(sql, favoriteIngredientRowMapper, email);
    }
    public List<Recipe> findUserAllFavoriteRecipe(String email){
        userHelper.emailExist(email);
        String sql = "Select r.* from favorite as f" +
                "join recipe r on f.favoriteable_id = r.id_recipe" +
                "where f.favoriteable_type = 'recipe' " +
                "and f.favoriteable_id = ?;";
        return jdbcTemplate.query(sql,recipeRowMapper,email);
    }
}
