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

    public List<RecipeDietsDto> orderBy(String email, String order) {
        String sql = """
                SELECT\s
                        r.id_recipe, r.email, r.title, r.content, r.image, r.person,\s
                        r.state, r.rate, r.nb_rate, r.create_time, r.update_time,\s
                        CASE \s
                            WHEN COUNT(DISTINCT d.id_diet) = 0 THEN 'Non classé' \s
                            WHEN SUM(CASE WHEN d.name = 'Végan' THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient) \s
                                THEN 'Végan' \s
                            WHEN SUM(CASE WHEN d.name IN ('Végétarien') THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient) \s
                                THEN 'Végétarien' \s
                            ELSE 'Non végétarien' \s
                        END AS diet, \s
                        CASE \s
                            WHEN COUNT(CASE WHEN f.email = ? THEN 1 ELSE NULL END) > 0 THEN 'true' \s
                            ELSE 'false' \s
                        END AS is_favorite \s
                    FROM recipe AS r \s
                    JOIN recipe_ingredient ri ON r.id_recipe = ri.id_recipe \s
                    JOIN ingredient i ON ri.id_ingredient = i.id_ingredient \s
                    LEFT JOIN diet_ingredient di ON ri.id_ingredient = di.id_ingredient \s
                    LEFT JOIN diet d ON di.id_diet = d.id_diet \s
                    LEFT JOIN opinion o ON r.id_recipe = o.id_recipe \s
                    LEFT JOIN favorite f ON r.id_recipe = f.favoriteable_id \s
                        AND f.favoriteable_type = 'recipe' \s
                    GROUP BY\s
                        r.id_recipe, r.email, r.title, r.content, r.image, r.person,\s
                        r.state, r.rate, r.nb_rate, r.create_time, r.update_time \s
                        order by """+order+ """ 
                DESC
                """;
        List<RecipeDietsDto> recipes = jdbcTemplate.query(sql, recipeRowMapper, email);
        return recipes;

    }
    public List<RecipeDietsDto> where(String email, String condition) {
        String sql = """
                SELECT\s
                    r.id_recipe, r.email, r.title, r.content, r.image, r.person,\s
                    r.state, r.rate, r.nb_rate, r.create_time, r.update_time,\s
                    CASE \s
                        WHEN COUNT(DISTINCT d.id_diet) = 0 THEN 'Non classé' \s
                        WHEN SUM(CASE WHEN d.name = 'Végan' THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient) \s
                            THEN 'Végan' \s
                        WHEN SUM(CASE WHEN d.name IN ('Végétarien') THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient) \s
                            THEN 'Végétarien' \s
                        ELSE 'Non végétarien' \s
                    END AS diet, \s
                    CASE \s
                        WHEN COUNT(CASE WHEN f.email = ? THEN 1 ELSE NULL END) > 0 THEN 'true' \s
                        ELSE 'false' \s
                    END AS is_favorite \s
                FROM recipe AS r \s
                JOIN recipe_ingredient ri ON r.id_recipe = ri.id_recipe \s
                JOIN ingredient i ON ri.id_ingredient = i.id_ingredient \s
                LEFT JOIN diet_ingredient di ON ri.id_ingredient = di.id_ingredient \s
                LEFT JOIN diet d ON di.id_diet = d.id_diet \s
                LEFT JOIN opinion o ON r.id_recipe = o.id_recipe \s
                LEFT JOIN favorite f ON r.id_recipe = f.favoriteable_id \s
                    AND f.favoriteable_type = 'recipe' \s
                where """+condition+ """
                GROUP BY\s
                    r.id_recipe, r.email, r.title, r.content, r.image, r.person,\s
                    r.state, r.rate, r.nb_rate, r.create_time, r.update_time \s
                """;
        List<RecipeDietsDto> recipes = jdbcTemplate.query(sql, recipeRowMapper, email);
        return recipes;

    }

    public List<RecipeDietsDto> having(String email, String diet) {
        String sql = """
                    SELECT 
                        r.id_recipe, r.email, r.title, r.content, r.image, r.person, 
                        r.state, r.rate, r.nb_rate, r.create_time, r.update_time, 
                        CASE  
                            WHEN COUNT(DISTINCT d.id_diet) = 0 THEN 'Non classé'  
                            WHEN SUM(CASE WHEN d.name = 'Végan' THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient)  
                                THEN 'Végan'  
                            WHEN SUM(CASE WHEN d.name IN ('Végétarien') THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient)  
                                THEN 'Végétarien'  
                            ELSE 'Non végétarien'  
                        END AS diet,  
                        CASE  
                            WHEN COUNT(CASE WHEN f.email = ? THEN 1 ELSE NULL END) > 0 THEN 'true'  
                            ELSE 'false'  
                        END AS is_favorite  
                    FROM recipe AS r  
                    JOIN recipe_ingredient ri ON r.id_recipe = ri.id_recipe  
                    JOIN ingredient i ON ri.id_ingredient = i.id_ingredient  
                    LEFT JOIN diet_ingredient di ON ri.id_ingredient = di.id_ingredient  
                    LEFT JOIN diet d ON di.id_diet = d.id_diet  
                    LEFT JOIN opinion o ON r.id_recipe = o.id_recipe  
                    LEFT JOIN favorite f ON r.id_recipe = f.favoriteable_id  
                        AND f.favoriteable_type = 'recipe'  
                    GROUP BY 
                        r.id_recipe, r.email, r.title, r.content, r.image, r.person, 
                        r.state, r.rate, r.nb_rate, r.create_time, r.update_time  
                    HAVING diet = '""" +diet+"'";
        List<RecipeDietsDto> recipes = jdbcTemplate.query(sql, recipeRowMapper, email);
        return recipes;
    }
}
