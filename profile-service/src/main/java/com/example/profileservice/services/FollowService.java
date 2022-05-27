package com.example.profileservice.services;

import pe.edu.upc.entities.Follow;

public interface FollowService extends CrudService<Follow,Long> {

    Follow createFollow(Follow follow, Long chefId, Long readerId);
}
