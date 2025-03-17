package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.dtos.SearchResultRecipeDto;
import com.fmg.gmf_core.entitys.Recipe;
import com.fmg.gmf_core.helpers.GlobalHelper;
import com.fmg.gmf_core.helpers.IngredientHelper;
import com.fmg.gmf_core.helpers.UserHelper;
import com.fmg.gmf_core.services.DateTimeService;
import com.fmg.gmf_core.services.RemoveAccentService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class RecipeDao {
    private final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;
    private final UserHelper userHelper;
    private final DateTimeService dateTimeService;
    private final IngredientHelper ingredientHelper;
    private final RemoveAccentService removeAccentService;


    public RecipeDao(JdbcTemplate jdbcTemplate, UserDao userDao, GlobalHelper globalHelper, UserHelper userHelper, DateTimeService dateTimeService, IngredientHelper ingredientHelper, RemoveAccentService removeAccents){
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
        this.userHelper = userHelper;
        this.dateTimeService = dateTimeService;
        this.ingredientHelper = ingredientHelper;
        this.removeAccentService = removeAccents;
    }
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
    private final RowMapper<SearchResultRecipeDto> searchResultRecipeDtoRowMapper =(rs, _)-> new SearchResultRecipeDto (
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
            rs.getInt("matching_ingredients"),
            rs.getString("diet"),
            rs.getString("is_favorite")

    );

    public List<Recipe> findAll() {
        String sql = "SELECT * FROM recipe";
        List<Recipe> recipes = jdbcTemplate.query(sql, recipeRowMapper);
        globalHelper.isEmpty(recipes, "recette");
        return recipes ;
    }

    public int save(Recipe recipe) {
        globalHelper.notExist(recipeExist(recipe.getTitle()),"Recette");
        userHelper.emailExist(recipe.getEmail());
        String searchTitle = removeAccentService.removeAccent(recipe.getTitle());
        String sql = "INSERT INTO recipe (email, title, search_title, content ,image , person, create_time, update_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, recipe.getEmail(), recipe.getTitle(),searchTitle,recipe.getContent(),recipe.getImage(),recipe.getPerson(),dateTimeService.getCurrentDateTime(), dateTimeService.getCurrentDateTime());
        return findRecipeIdByName(recipe.getTitle());
    }
    public List<SearchResultRecipeDto> findRecipesByIngredientsAndName(List<Integer> ingredientIds, String search, String email) {
        StringBuilder sql = new StringBuilder(
                "SELECT r.id_recipe, r.email, r.title, r.content, r.image, r.person, " +
                        "r.state, r.rate, r.nb_rate, r.create_time, r.update_time, " +
                        "COUNT(ri.id_ingredient) AS matching_ingredients, " +
                        """
                        CASE\s
                                -- Si tous les ingrédients de la recette sont Végan
                                WHEN COUNT(DISTINCT CASE WHEN d.name = 'Végan' THEN i.id_ingredient END) = COUNT(DISTINCT ri.id_ingredient)\s
                                    THEN 'Végan'
                                -- Si tous les ingrédients de la recette sont soit Végan soit Végétarien
                                WHEN COUNT(DISTINCT CASE WHEN d.name IN ('Végan', 'Végétarien') THEN i.id_ingredient END) = COUNT(DISTINCT ri.id_ingredient)\s
                                    THEN 'Végétarien'
                                -- Si au moins un ingrédient n'a pas de régime alimentaire spécifié
                                ELSE 'Non renseigné'
                        END AS diet,
                                """+
                        "CASE WHEN COUNT(f.favoriteable_id) > 0 THEN 'true' ELSE 'false' END AS is_favorite " +
                        "FROM recipe r " +
                        "LEFT JOIN recipe_ingredient ri ON r.id_recipe = ri.id_recipe " +
                        "JOIN ingredient i ON ri.id_ingredient = i.id_ingredient " +
                        "LEFT JOIN diet_ingredient di ON ri.id_ingredient = di.id_ingredient " +
                        "LEFT JOIN diet d ON di.id_diet = d.id_diet " +
                        "LEFT JOIN opinion o ON r.id_recipe = o.id_recipe " +
                        "LEFT JOIN favorite f ON r.id_recipe = f.favoriteable_id AND f.favoriteable_type = 'recipe' " // Correction ici
        );

        List<Object> params = new ArrayList<>();
        boolean hasWhere = false; // Flag pour savoir si un WHERE existe déjà

        // Filtre sur les ingrédients
        if (ingredientIds != null && !ingredientIds.isEmpty()) {
            for (Integer ingredientId : ingredientIds) {
                ingredientHelper.ingredientExist(ingredientId); // Vérifie si l'ingrédient existe
            }
            String placeholders = String.join(",", Collections.nCopies(ingredientIds.size(), "?"));
            sql.append(" WHERE ri.id_ingredient IN (").append(placeholders).append(") ");
            params.addAll(ingredientIds);
            hasWhere = true;
        }

        // Filtre sur le titre
        if (search != null && !search.isBlank()) {
            sql.append(hasWhere ? "AND " : " WHERE ");
            sql.append("r.search_title LIKE ? ");
            params.add("%" + search + "%");
            hasWhere = true;
        }

        // Filtre sur les favoris (optionnel)
        if (email != null) {
            sql.append(hasWhere ? "AND " : " WHERE ");
            sql.append("(f.email = ? OR f.email IS NULL) ");
            params.add(email);
        }

        // Ajout du GROUP BY et ORDER BY
        sql.append(" GROUP BY r.id_recipe ORDER BY matching_ingredients DESC, r.rate DESC");

        try {
            return jdbcTemplate.query(sql.toString(), searchResultRecipeDtoRowMapper, params.toArray());
        } catch (Exception e) {
            System.out.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
            throw e;
        }
    }


    public Recipe findRecipeById (int id){
        String sql = "SELECT * from recipe where id_recipe = ?";
        globalHelper.exist(!recipeIdExist(id), "Recette");
        return jdbcTemplate.queryForObject(sql, recipeRowMapper, id);
    }
    public int updateRecipe (Recipe recipe){
        //userHelper.emailExist(recipe.getEmail());
        globalHelper.exist(!recipeIdExist(recipe.getId_recipe()), "Recette");
        String sql = "UPDATE recipe set email = ?, title = ?, content = ?, image = ?, person = ?, state = ?, rate = ?, nb_rate = ? where id_recipe = ?;";
        jdbcTemplate.update(sql, recipe.getEmail(), recipe.getTitle(), recipe.getContent(), recipe.getImage(), recipe.getPerson(), recipe.getState(), recipe.getRate(), recipe.getNb_rate(), recipe.getId_recipe());
        return recipe.getId_recipe();
    }
    public void deleteRecipe(int recipeId, String email) {
        String sql = "DELETE FROM recipe WHERE id_recipe = ? AND email = ?";
        jdbcTemplate.update(sql, recipeId, email);
    }



    //Utilitaires
    public boolean recipeExist(String title) {
        String checkSql = "SELECT COUNT(*) FROM recipe WHERE title = ? ";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, title);
        return count > 0;
    }
    public boolean recipeIdExist(int id) {
        String checkSql = "SELECT COUNT(*) FROM recipe WHERE id_recipe = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);
        return count > 0;
    }
    private int findRecipeIdByName(String title){
        String sql = "SELECT id_recipe FROM recipe where title = ?";
        int id_recipe = jdbcTemplate.queryForObject(sql,Integer.class, title);
        return id_recipe;
    }

}
