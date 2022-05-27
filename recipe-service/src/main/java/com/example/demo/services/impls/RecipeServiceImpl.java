package com.example.demo.services.impls;


import com.example.demo.entities.Ingredient;
import com.example.demo.entities.Recipe;
import com.example.demo.entities.Tag;
import com.example.demo.respositories.*;
import com.example.demo.services.RecipeService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private MultimediaRepository multimediaRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private CommentRepository commentRepository;

   @Override
   public List<Recipe> findAll() throws Exception {
       return recipeRepository.findAll();
   }

    @Override
    public Recipe findById(Long aLong) throws Exception {
        return recipeRepository.findById(aLong)
                .orElseThrow(()->new ResourceNotFoundException("Recipe","Id",aLong));
    }

    @Override
    public Recipe update(Recipe entity, Long aLong) throws Exception {
       Recipe recipe = recipeRepository.findById(aLong)
               .orElseThrow(()->new ResourceNotFoundException("Recipe","Id",aLong));
       recipe.setName(entity.getName())
               .setExclusive(entity.isExclusive())
               .setPreparation(entity.getPreparation())
               .setRecommendation(entity.getRecommendation())
               .setViews(entity.getViews());
       return recipeRepository.save(recipe);
    }

    @Override
    public void deleteById(Long aLong) throws Exception {
        Recipe recipe = recipeRepository.findById(aLong)
                .orElseThrow(()->new ResourceNotFoundException("Recipe","Id",aLong));
        recipeRepository.deleteById(aLong);
    }


    @Override
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Tag> getAllTagsByRecipeId(Long recipeId) {
        return recipeRepository.findById(recipeId).map(
                recipe -> {
                    List<Tag> tags=recipe.getTags();
                    return tags;
                }
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public List<Ingredient> getAllIngredientsByRecipeId(Long recipeId) {
        return recipeRepository.findById(recipeId).map(
                recipe -> {
                    List<Ingredient> ingredients=recipe.getIngredients();
                    return ingredients;
                }
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public Recipe assignRecipeTag(Long recipeId, Long tagId) {
        Tag tag=tagRepository.findById(tagId)
                .orElseThrow(()->new ResourceNotFoundException("Tag","Id",tagId));
        return recipeRepository.findById(recipeId).map(
                recipe -> recipeRepository.save(recipe.assignTag(tag))
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public Recipe unassignRecipeTag(Long recipeId, Long tagId) {
        Tag tag=tagRepository.findById(tagId)
                .orElseThrow(()->new ResourceNotFoundException("Tag","Id",tagId));
        return recipeRepository.findById(recipeId).map(
                recipe -> recipeRepository.save(recipe.unAssignTag(tag))
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public Recipe assignRecipeIngredient(Long recipeId, Long ingredientId) {
        Ingredient ingredient=ingredientRepository.findById(ingredientId)
                .orElseThrow(()->new ResourceNotFoundException("Ingredient","Id",ingredientId));
        return recipeRepository.findById(recipeId).map(
                recipe -> recipeRepository.save(recipe.assignIngredient(ingredient))
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public Recipe unassignRecipeIngredient(Long recipeId, Long ingredientId) {
        Ingredient ingredient=ingredientRepository.findById(ingredientId)
                .orElseThrow(()->new ResourceNotFoundException("Ingredient","Id",ingredientId));
        return recipeRepository.findById(recipeId).map(
                recipe -> recipeRepository.save(recipe.unassignIngredient(ingredient))
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }
}
