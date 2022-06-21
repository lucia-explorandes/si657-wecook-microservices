package pe.edu.upc.services.impls;


import pe.edu.upc.client.ProfileClient;
import pe.edu.upc.entities.Comment;
import pe.edu.upc.entities.Recipe;
import pe.edu.upc.model.Profile;
import pe.edu.upc.respositories.CommentRepository;
import pe.edu.upc.respositories.RecipeRepository;
import pe.edu.upc.services.CommentService;
import pe.edu.upc.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RecipeRepository recipeRepository;


    @Qualifier("pe.edu.upc.client.ProfileClient")
    @Autowired
    ProfileClient profileClient;

    @Override
    public Comment createComment(Long recipeId,  Comment comment) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));

        return commentRepository.save(comment.setRecipe(recipe));
    }

    @Override
    public List<Comment> getAllCommentsByRecipeId(Long recipeId) {
        return commentRepository.findCommentByRecipeId(recipeId);
    }

    @Override
    public List<Comment> findAll() throws Exception {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long aLong) throws Exception {
        Comment comment = commentRepository.findById(aLong).orElse(null);
        if (null!=comment){
            Profile profile = profileClient.getProfile(comment.getProfileId()).getBody();
            comment.setProfile(profile);
        }
        return comment;
    }

    @Override
    public Comment update(Comment entity, Long aLong) throws Exception {
        Comment comment = commentRepository.findById(aLong)
                .orElseThrow(()->new ResourceNotFoundException(""));

        comment.setText(entity.getText());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long aLong) throws Exception {
        Comment comment = commentRepository.findById(aLong)
                .orElseThrow(()->new ResourceNotFoundException(""));
        commentRepository.deleteById(aLong);
    }
}

