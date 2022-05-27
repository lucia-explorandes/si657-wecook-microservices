package com.example.demo.services;


import com.example.demo.entities.Recipe;
import com.example.demo.entities.Tag;

import java.util.List;

public interface TagService extends CrudService<Tag,Long>{

    Tag createTag(Tag tag);

    List<Recipe> getAllRecipesByTagId(Long tagId);

    Tag getTagByName(String name);
}

