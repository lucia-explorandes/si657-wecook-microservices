package pe.edu.upc.services;


import pe.edu.upc.entities.Score;

import java.util.List;

public interface ScoreService extends CrudService<Score,Long>{/*
    List<Score> getAllScoresByRecipeId(Long recipeId);*/
    Score createScore(Long recipeId, Score score);
}

