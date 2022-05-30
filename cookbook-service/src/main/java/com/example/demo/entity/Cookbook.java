package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="cookbook")
public class Cookbook{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    /*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonIgnore
    private Profile profile;*/

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "cookbook_recipe", joinColumns = {@JoinColumn(name = "cookbook_id")},
    inverseJoinColumns = {@JoinColumn(name = "recipe_id")})
    private List<Recipe> recipes;*/

    private boolean favourite;

    public Long getId() {
        return id;
    }

    public Cookbook setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Cookbook setName(String name) {
        this.name = name;
        return this;
    }

    /*public Profile getProfile() {
        return profile;
    }

    public Cookbook setProfile(Profile profile) {
        this.profile = profile;
        return this;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
*/

    public boolean isFavourite() {
        return favourite;
    }

    public Cookbook setFavourite(boolean favourite) {
        this.favourite = favourite;
        return this;
    }
}
