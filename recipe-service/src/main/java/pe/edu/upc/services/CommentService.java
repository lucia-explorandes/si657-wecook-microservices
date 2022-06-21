package pe.edu.upc.services;


import pe.edu.upc.entities.Comment;

import java.util.List;

public interface CommentService extends CrudService<Comment,Long>{
    List<Comment> getAllCommentsByRecipeId(Long recipeId);
    Comment createComment(Long recipeId, Comment comment);
}

