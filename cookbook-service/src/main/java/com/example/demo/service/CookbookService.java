package com.example.demo.service;

import com.example.demo.entity.Cookbook;

public interface CookbookService extends CrudService<Cookbook, Long> {
    Cookbook createCookbook(Cookbook cookbook);

    Cookbook getCookbookByName(String name);
}
