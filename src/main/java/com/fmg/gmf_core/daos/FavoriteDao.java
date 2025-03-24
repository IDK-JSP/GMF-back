package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.dtos.RecipeDietsDto;
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
    private final RowMapper<RecipeDietsDto> recipeDietsDtoRowMapper = (rs, rowNum) -> new RecipeDietsDto(
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
            rs.getTimestamp("update_time").toLocalDateTime(),
            rs.getString("diet"),
            rs.getString("is_favorite")
    );

    public List<Ingredient> findUserAllFavoriteIngredient(String email){
        userHelper.emailExist(email);
        String sql = "SELECT i.* FROM favorite as f " +
                "join ingredient i on f.favoriteable_id = i.id_ingredient " +
                "where f.favoriteable_type = 'ingredient' " +
                "and f.email = ?";
        return  jdbcTemplate.query(sql, ingredientRowMapper, email);
    }
    public List<RecipeDietsDto> findUserAllFavoriteRecipe(String email){
        userHelper.emailExist(email);
        String sql = """
                SELECT
                	r.id_recipe, r.email, r.title, r.content, r.image, r.person,
                	r.state, r.rate, r.nb_rate, r.create_time, r.update_time,
                	CASE
                		-- Si tous les ingrédients de la recette sont Végan
                		WHEN COUNT(DISTINCT CASE WHEN d.name = 'Végan' THEN i.id_ingredient END) = COUNT(DISTINCT ri.id_ingredient)
                		THEN 'Végan'
                		-- Si tous les ingrédients de la recette sont soit Végan soit Végétarien
                		WHEN COUNT(DISTINCT CASE WHEN d.name IN ('Végan', 'Végétarien') THEN i.id_ingredient END) = COUNT(DISTINCT ri.id_ingredient)
                		THEN 'Végétarien'
                		-- Si au moins un ingrédient n'a pas de régime alimentaire spécifié
                		ELSE 'Non renseigné'
                		END AS diet,
                	CASE
                	WHEN COUNT(CASE WHEN f.email = ? THEN 1 ELSE NULL END) > 0 THEN 'true'
                	ELSE 'false'
                	END AS is_favorite
                	FROM recipe AS r
                	LEFT JOIN recipe_ingredient ri ON r.id_recipe = ri.id_recipe
                	LEFT JOIN ingredient i ON ri.id_ingredient = i.id_ingredient
                	LEFT JOIN diet_ingredient di ON ri.id_ingredient = di.id_ingredient
                	LEFT JOIN diet d ON di.id_diet = d.id_diet
                	LEFT JOIN opinion o ON r.id_recipe = o.id_recipe
                	LEFT JOIN favorite f ON r.id_recipe = f.favoriteable_id
                	where f.favoriteable_type = 'recipe'\s
                	and f.email = ?
                	GROUP BY
                	r.id_recipe, r.email, r.title, r.content, r.image, r.person,
                	r.state, r.rate, r.nb_rate, r.create_time, r.update_time
                
                """;
        return  jdbcTemplate.query(sql, recipeDietsDtoRowMapper, email, email);
    }
    public List<Favorite> findRecipeFavorite(int id){
        recipeHelper.recipeExist(id);
        String sql = "SELECT * FROM favorite where favoriteable_type = 'recipe' and favoriteable_id = ?";
        List<Favorite> favorites = jdbcTemplate.query(sql, favoriteRowMapper, id);
        return favorites;
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
