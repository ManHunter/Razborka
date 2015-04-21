package com.razborka.service.Impl;

import com.razborka.dao.ReviewDao;
import com.razborka.model.Review;
import com.razborka.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 19.04.2015.
 */
@Service("reviewService")
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public void saveReview(Review review) {
        reviewDao.save(review);
    }

    @Override
    public void updateReview(Review review) {
        reviewDao.update(review);
    }

    @Override
    public void deleteReview(int id) {
        reviewDao.deleteById(id);
    }

    @Override
    public List<Review> getAllReview() {
        return reviewDao.getAll();
    }

    @Override
    public Review getReviewById(int id) {
        return reviewDao.get(id);
    }

    @Override
    public List<Review> getReviewByUserId(int user_id) {
        return reviewDao.getReviewByUserId(user_id);
    }
}
