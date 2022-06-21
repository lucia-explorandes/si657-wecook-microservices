package pe.edu.upc.services;


import pe.edu.upc.entities.Ingredient;
import pe.edu.upc.entities.Recipe;
import pe.edu.upc.entities.Tag;

import java.util.List;

public interface RecipeService extends CrudService<Recipe,Long> {

    Recipe createRecipe(Recipe recipe);
    List<Tag> getAllTagsByRecipeId(Long recipeId);

    List<Ingredient> getAllIngredientsByRecipeId(Long recipeId);

    Recipe assignRecipeTag(Long recipeId, Long tagId);

    Recipe unassignRecipeTag(Long recipeId, Long tagId);

    Recipe assignRecipeIngredient(Long recipeId, Long ingredientId);

    Recipe unassignRecipeIngredient(Long recipeId, Long ingredientId);

    List<Recipe> getAllRecipesByProfileId(Long profileId);

    List<Recipe> getAllRecipesByCookbookId(Long cookbookId);
}

