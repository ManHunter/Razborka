package com.razborka.service;

import com.razborka.model.Review;

import java.util.List;

/**
 * Created by Admin on 19.04.2015.
 */
public interface ReviewService {

    public void saveReview(Review review);

    public void updateReview(Review review);

    public void deleteReview(int id);

    public List<Review> getAllReview();

    public Review getReviewById(int id);

    public List<Review> getReviewByUserId(int user_id);
}