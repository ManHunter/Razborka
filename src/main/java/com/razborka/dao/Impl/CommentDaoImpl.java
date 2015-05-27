package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.CommentDao;
import com.razborka.model.Comment;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 01.05.2015.
 */
@Repository("commentDao")
public class CommentDaoImpl extends AbstractDao<Comment> implements CommentDao {
    @Override
    public List<Comment> getAllCommentsByCarId(int car_id) {
        return getSession().createCriteria(Comment.class)
                .add(Restrictions.eq("car.id", car_id))
                .list();
    }

    @Override
    public List<Comment> getAllCommentsByPartId(int part_id) {
        return getSession().createCriteria(Comment.class)
                .add(Restrictions.eq("part.id", part_id))
                .list();
    }

    public List<Comment> getUserPartComments(int user_id) {

        Query query = getSession().createQuery("select com from Comment com" +
                " inner join com.part p" +
                " inner join p.car c" +
                " where c.user.id=:user_id")
                .setParameter("user_id", user_id);

        return query.list();
    }

    public List<Comment> getUserCarComments(int user_id) {
        Query query = getSession().createQuery("select com from Comment com" +
                " inner join com.car c" +
                " where c.user.id=:user_id")
                .setParameter("user_id", user_id);

        return query.list();
    }
}
