package com.razborka.dao;

import com.razborka.model.Review;

import java.util.List;

/**
 * Created by Admin on 19.04.2015.
 */
public interface ReviewDao extends Dao<Review> {
    public List<Review> getReviewByUserId(int user_id);
}
