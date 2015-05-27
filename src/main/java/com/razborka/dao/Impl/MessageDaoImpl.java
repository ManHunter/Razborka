package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.MessageDao;
import com.razborka.model.Message;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 09.05.2015.
 */
@Repository("messageDao")
public class MessageDaoImpl extends AbstractDao<Message> implements MessageDao {
    @Override
    public List<Message> getInboxMessage(int user_id) {
        return getSession().createCriteria(Message.class)
                .add(Restrictions.eq("user.id", user_id))
                .list();
    }

    @Override
    public List<Message> getOutboxMessage(int user_id) {
        return getSession().createCriteria(Message.class)
                .add(Restrictions.eq("user_from.id", user_id))
                .list();
    }

    @Override
    public List<Message> getAllMessage(int user_id) {
        return getSession().createCriteria(Message.class)
                .add(Restrictions.eq("user.id", user_id))
                .add(Restrictions.eq("user_from.id", user_id))
                .list();
    }
}
