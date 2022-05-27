package com.example.demo.services;


import com.example.demo.entities.Multimedia;

import java.util.List;

public interface MultimediaService extends CrudService<Multimedia,Long> {

    Multimedia createMultimedia(Long recipeId,Multimedia multimedia);

    List<Multimedia> getAllMultimediaByRecipeId(Long recipeId);
}
