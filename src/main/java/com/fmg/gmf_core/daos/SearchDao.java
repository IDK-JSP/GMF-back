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

    // 🔥 Requête SQL commune pour toutes les méthodes
    private static final String BASE_SQL = """
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
                WHEN COUNT(f.favoriteable_id) > 0 THEN 'true'  
                ELSE 'false'  
            END AS is_favorite  
        FROM recipe AS r  
        JOIN recipe_ingredient ri ON r.id_recipe = ri.id_recipe  
        JOIN ingredient i ON ri.id_ingredient = i.id_ingredient  
        LEFT JOIN diet_ingredient di ON ri.id_ingredient = di.id_ingredient  
        LEFT JOIN diet d ON di.id_diet = d.id_diet  
        LEFT JOIN opinion o ON r.id_recipe = o.id_recipe  
        LEFT JOIN favorite f ON r.id_recipe = f.favoriteable_id  
            AND f.email = ?  
            AND f.favoriteable_type = 'recipe'  
        GROUP BY 
            r.id_recipe, r.email, r.title, r.content, r.image, r.person, 
            r.state, r.rate, r.nb_rate, r.create_time, r.update_time  
    """;

    // 🔥 Méthode générique pour exécuter une requête SQL personnalisée
    private List<RecipeDietsDto> executeQuery(String additionalClause, Object... params) {
        String sql = BASE_SQL + " " + additionalClause;
        List<RecipeDietsDto> recipes = jdbcTemplate.query(sql, recipeRowMapper, params);
        globalHelper.isEmpty(recipes, "recette");
        return recipes;
    }

    // 🔹 Trier par un critère donné (ex: "rate", "create_time")
    public List<RecipeDietsDto> orderBy(String order, String email) {
        return executeQuery("ORDER BY " + order + " DESC", email);
    }

    // 🔹 Filtrer par un régime alimentaire (ex: "Végétarien", "Végan")
    public List<RecipeDietsDto> having(String diet, String email) {
        return executeQuery("HAVING SUM(CASE WHEN d.name IN (?) THEN 1 ELSE 0 END) = COUNT(DISTINCT i.id_ingredient)", email, diet);
    }

    // 🔹 Ajouter une condition personnalisée (ex: "state = 'published'")
    public List<RecipeDietsDto> where(String condition, String email) {
        return executeQuery("WHERE " + condition, email);
    }
}
