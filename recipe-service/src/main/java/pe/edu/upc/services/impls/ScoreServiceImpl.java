package pe.edu.upc.services.impls;


import pe.edu.upc.client.ProfileClient;
import pe.edu.upc.entities.Comment;
import pe.edu.upc.entities.Score;
import pe.edu.upc.model.Profile;
import pe.edu.upc.respositories.RecipeRepository;
import pe.edu.upc.respositories.ScoreRepository;
import pe.edu.upc.services.ScoreService;
import pe.edu.upc.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Qualifier("pe.edu.upc.client.ProfileClient")
    @Autowired
    ProfileClient profileClient;
    @Autowired
    private RecipeRepository recipeRepository;
/*
    @Override
    public List<Score> getAllScoresByRecipeId(Long recipeId) {
        return scoreRepository.findByRecipeId(recipeId);
    }
*/
    @Override
    public Score createScore(Long recipeId, Score score) {
        return recipeRepository.findById(recipeId).map(
                recipe -> {score.setRecipe(recipe);
                    return scoreRepository.save(score);}
        ).orElseThrow(()-> new ResourceNotFoundException("Recipe", "Id", recipeId));
    }

    @Override
    public List<Score> findAll() throws Exception {
        return scoreRepository.findAll();
    }

    @Override
    public Score findById(Long aLong) throws Exception {
        Score score = scoreRepository.findById(aLong).orElse(null);
        if (null!=score){
            Profile profile = profileClient.getProfile(score.getProfileId()).getBody();
            score.setProfile(profile);
        }
        return score;
    }

    @Override
    public Score update(Score entity, Long aLong) throws Exception {
        Score score = scoreRepository.findById(aLong)
                .orElseThrow(()->new ResourceNotFoundException(""));
        score.setStarQuantity(entity.getStarQuantity());
        return scoreRepository.save(score);
    }

    @Override
    public void deleteById(Long aLong) throws Exception {
        Score score = scoreRepository.findById(aLong)
                .orElseThrow(()->new ResourceNotFoundException(""));
        scoreRepository.deleteById(aLong);
    }
}