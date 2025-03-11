package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Favorite;
import com.fmg.gmf_core.entitys.Ingredient;
import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.helpers.RecipeHelper;
import com.fmg.gmf_core.helpers.UserHelper;
import com.fmg.gmf_core.services.DateTimeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class FavoriteDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserHelper userHelper;
    private final GlobalHelper globalHelper;
    private final DateTimeService dateTimeService;
    private final RecipeHelper recipeHelper;

    public FavoriteDao(JdbcTemplate jdbcTemplate, UserHelper userHelper, GlobalHelper globalHelper, DateTimeService dateTimeService, RecipeHelper recipeHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userHelper = userHelper;
        this.globalHelper = globalHelper;
        this.dateTimeService = dateTimeService;
        this.recipeHelper = recipeHelper;
    }
    private final RowMapper<Favorite> favoriteRowMapper = (rs, _) -> new Favorite(
        rs.getString("email"),
        rs.getString("favoriteable_type"),
        rs.getInt("favoriteable_id"),
        rs.getTimestamp("create_time").toLocalDateTime()
    );
    private final RowMapper<Ingredient> ingredientRowMapper = (rs, _) -> new Ingredient(
            rs.getInt("id_ingredient"),
            rs.getString("name"),
            rs.getString("content"),
            rs.getTimestamp("create_time").toLocalDateTime(),
            rs.getTimestamp("update_time").toLocalDateTime()
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

    public List<Ingredient> findUserAllFavoriteIngredient(String email){
        userHelper.emailExist(email);
        String sql = "SELECT i.* FROM favorite as f " +
                "join ingredient i on f.favoriteable_id = i.id_ingredient " +
                "where f.favoriteable_type = 'ingredient' " +
                "and f.email = ?";
        return  jdbcTemplate.query(sql, ingredientRowMapper, email);
    }
    public List<Recipe> findUserAllFavoriteRecipe(String email){
        userHelper.emailExist(email);
        String sql = "SELECT r.* FROM favorite as f " +
                "join recipe r on f.favoriteable_id = r.id_recipe " +
                "where f.favoriteable_type = 'recipe' " +
                "and f.email = ?;";
        return  jdbcTemplate.query(sql, recipeRowMapper, email);
    }
    public Favorite save(Favorite favorite) {
        userHelper.emailExist(favorite.getEmail());
        globalHelper.notExist(favoriteExist(favorite.getEmail(),favorite.getFavoriteable_type(),favorite.getFavoriteable_id()), "Favoris");
        String sql = "INSERT INTO favorite (email, favoriteable_type, favoriteable_id, create_time) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, favorite.getEmail(),favorite.getFavoriteable_type(),favorite.getFavoriteable_id(), dateTimeService.getCurrentDateTime());
        return favorite;
    }
    public void deleteFavorite(int recipeId, String email,String type) {
        String sql = "DELETE FROM favorite WHERE favoriteable_id = ? AND email = ? AND favoriteable_type = '"+type+"'";
        jdbcTemplate.update(sql, recipeId, email);
    }

    //Utilitaire
    public boolean favoriteExist(String email, String favoriteable_type, int favoriteable_id) {
        String checkSql = "SELECT COUNT(*) FROM favorite WHERE email = ? and favoriteable_type = ? and favoriteable_id= ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, email, favoriteable_type, favoriteable_id);
        return count > 0;
    }
}
