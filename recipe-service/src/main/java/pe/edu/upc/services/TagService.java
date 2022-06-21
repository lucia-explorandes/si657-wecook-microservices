package pe.edu.upc.services;


import pe.edu.upc.entities.Recipe;
import pe.edu.upc.entities.Tag;

import java.util.List;

public interface TagService extends CrudService<Tag,Long>{

    Tag createTag(Tag tag);

    List<Recipe> getAllRecipesByTagId(Long tagId);

    Tag getTagByName(String name);
}

