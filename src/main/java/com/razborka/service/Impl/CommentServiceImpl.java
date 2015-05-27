package com.razborka.service.Impl;

import com.razborka.Constants;
import com.razborka.dao.CarDao;
import com.razborka.dao.CommentDao;
import com.razborka.model.Car;
import com.razborka.model.Comment;
import com.razborka.model.Part;
import com.razborka.model.User;
import com.razborka.service.CommentService;
import com.razborka.service.PartService;
import com.razborka.util.EmailSender;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 01.05.2015.
 */
@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private PartService partService;

    @Override
    public void saveComment(Comment comment) {
        comment.setDate(LocalDateTime.now());

        User user;
        Car car = comment.getCar();

        if(car != null) {
            car = carDao.get(comment.getCar().getId());
            user = car.getUser();
        } else {
            Part part = partService.getPartById(comment.getPart().getId());
            car = carDao.get(part.getCar().getId());
            user = car.getUser();
        }

        commentDao.save(comment);

        EmailSender sender = new EmailSender();
        sender.send(user, Constants.NEW_COMMENT);
    }

    @Override
    public void updateComment(Comment comment) {
        commentDao.update(comment);
    }

    @Override
    public void deleteComment(int id) {
        commentDao.deleteById(id);
    }

    @Override
    public Comment getCommentById(int id) {
        return commentDao.get(id);
    }

    @Override
    public List<Comment> getAllCommentsByCarId(int car_id) {
        return commentDao.getAllCommentsByCarId(car_id);
    }

    @Override
    public List<Comment> getAllCommentsByPartId(int part_id) {
        return commentDao.getAllCommentsByPartId(part_id);
    }

    @Override
    public List<Comment> getUserPartComments(int user_id) {
        return commentDao.getUserPartComments(user_id);
    }

    @Override
    public List<Comment> getUserCarComments(int user_id) {
        return commentDao.getUserCarComments(user_id);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDao.getAll();
    }
}
