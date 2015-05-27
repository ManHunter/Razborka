package com.razborka.service.Impl;

import com.razborka.Constants;
import com.razborka.dao.MessageDao;
import com.razborka.dao.UserDao;
import com.razborka.model.Message;
import com.razborka.service.MessageService;
import com.razborka.service.UserService;
import com.razborka.util.EmailSender;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 09.05.2015.
 */
@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void saveMessage(Message message) {
        message.setDate(LocalDateTime.now());

        EmailSender sender = new EmailSender();
        sender.send(userDao.get(message.getUser().getId()), Constants.NEW_MESSAGE);

        messageDao.save(message);
    }

    @Override
    public void deleteMessage(int id) {
        messageDao.deleteById(id);
    }

    @Override
    public Message getMessage(int id) {
        return messageDao.get(id);
    }

    @Override
    public List<Message> getInboxMessage(int user_id) {
        return messageDao.getInboxMessage(user_id);
    }

    @Override
    public List<Message> getOutboxMessage(int user_id) {
        return messageDao.getOutboxMessage(user_id);
    }
}
