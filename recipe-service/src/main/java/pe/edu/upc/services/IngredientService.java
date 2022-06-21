package pe.edu.upc.services;


import pe.edu.upc.entities.Ingredient;

import java.util.List;

public interface IngredientService extends CrudService<Ingredient,Long>{
    Ingredient createIngredient(Ingredient ingredient);

    Ingredient getIngredientByName(String name);
    List<Ingredient> getAllIngredientsByRecipeId(Long recipeId);
}

