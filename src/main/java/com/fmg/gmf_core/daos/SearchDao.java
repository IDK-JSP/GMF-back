package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.dtos.RecipeDietsDto;
import com.fmg.gmf_core.helpers.GlobalHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchDao {
    private final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;

    public SearchDao(JdbcTemplate jdbcTemplate, GlobalHelper globalHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
    }
    private final RowMapper<RecipeDietsDto> recipeRowMapper = (rs, rowNum) -> new RecipeDietsDto(
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
    public List<RecipeDietsDto> orderBy(String order,String email){
        String sql = "SELECT r.*," +
                "CASE " +
                " WHEN COUNT(DISTINCT d.id_diet) = 0 THEN 'Non classé' " +
                " WHEN SUM(CASE WHEN d.name = 'Végan' THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient) " +
                " THEN 'Végan' " +
                " WHEN SUM(CASE WHEN d.name IN ('Végétarien') THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient) " +
                " THEN 'Végétarien' " +
                " ELSE 'Non végétarien' " +
                "END AS diet, " +
                "CASE " +
                "WHEN COUNT(f.favoriteable_id) > 0 THEN 'true' " +
                "ELSE 'false' " +
                "END AS is_favorite "+
                "FROM recipe AS r "+
                "JOIN recipe_ingredient ri ON r.id_recipe = ri.id_recipe " +
                "JOIN ingredient i ON ri.id_ingredient = i.id_ingredient " +
                "LEFT JOIN diet_ingredient di ON ri.id_ingredient = di.id_ingredient " +
                "LEFT JOIN diet d ON di.id_diet = d.id_diet " +
                "left join opinion o on r.id_recipe = o.id_recipe "+
                "LEFT JOIN favorite f ON r.id_recipe = f.favoriteable_id AND f.email = ? "+
                "GROUP BY r.id_recipe " +
                "order by "+order+" DESC;";
        List<RecipeDietsDto> recipes = jdbcTemplate.query(sql,recipeRowMapper,email);
        globalHelper.isEmpty(recipes, "recette");
        return recipes;
    }
    public List<RecipeDietsDto> having(String diet, String email){
        String sql = "SELECT r.*," +
                "CASE " +
                " WHEN COUNT(DISTINCT d.id_diet) = 0 THEN 'Non classé' " +
                " WHEN SUM(CASE WHEN d.name = 'Végan' THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient) " +
                " THEN 'Végan' " +
                " WHEN SUM(CASE WHEN d.name IN ('Végétarien') THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient) " +
                " THEN 'Végétarien' " +
                " ELSE 'Non végétarien' " +
                "END AS diet, " +
                "CASE " +
                "WHEN COUNT(f.favoriteable_id) > 0 THEN 'true' " +
                "ELSE 'false' " +
                "END AS is_favorite "+
                "FROM recipe AS r " +
                "JOIN recipe_ingredient ri ON r.id_recipe = ri.id_recipe " +
                "JOIN ingredient i ON ri.id_ingredient = i.id_ingredient " +
                "LEFT JOIN diet_ingredient di ON ri.id_ingredient = di.id_ingredient " +
                "LEFT JOIN diet d ON di.id_diet = d.id_diet " +
                "left join opinion o on r.id_recipe = o.id_recipe "+
                "LEFT JOIN favorite f ON r.id_recipe = f.favoriteable_id AND f.email = ? "+
                "GROUP BY r.id_recipe " +
                "having diet = "+diet+";";
        List<RecipeDietsDto> recipes = jdbcTemplate.query(sql,recipeRowMapper,email);
        globalHelper.isEmpty(recipes, "recette");
        return recipes;
    }
    public List<RecipeDietsDto> where(String condition, String email){
        String sql = "SELECT r.*," +
                "CASE " +
                " WHEN COUNT(DISTINCT d.id_diet) = 0 THEN 'Non classé' " +
                " WHEN SUM(CASE WHEN d.name = 'Végan' THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient) " +
                " THEN 'Végan' " +
                " WHEN SUM(CASE WHEN d.name IN ('Végétarien') THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient) " +
                " THEN 'Végétarien' " +
                " ELSE 'Non végétarien' " +
                "END AS diet, " +
                "CASE " +
                "WHEN COUNT(f.favoriteable_id) > 0 THEN 'true' " +
                "ELSE 'false' " +
                "END AS is_favorite "+
                "FROM recipe AS r " +
                "JOIN recipe_ingredient ri ON r.id_recipe = ri.id_recipe " +
                "JOIN ingredient i ON ri.id_ingredient = i.id_ingredient " +
                "LEFT JOIN diet_ingredient di ON ri.id_ingredient = di.id_ingredient " +
                "LEFT JOIN diet d ON di.id_diet = d.id_diet " +
                "left join opinion o on r.id_recipe = o.id_recipe "+
                "LEFT JOIN favorite f ON r.id_recipe = f.favoriteable_id AND f.email = ? "+
                "where "+ condition+
                " GROUP BY r.id_recipe ";
        List<RecipeDietsDto> recipes = jdbcTemplate.query(sql,recipeRowMapper, email);
        globalHelper.isEmpty(recipes, "recette");
        return recipes;
    }
}
