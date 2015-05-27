package com.razborka.service.Impl;

import com.razborka.Constants;
import com.razborka.dao.RequestDao;
import com.razborka.model.Request;
import com.razborka.service.RequestService;
import com.razborka.util.EmailSender;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Service("requestService")
@Transactional
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestDao requestDao;

    @Override
    public void saveRequest(Request request) {
        request.setDate(LocalDateTime.now());

        if(request.getUser() != null) {
            EmailSender sender = new EmailSender();
            sender.send(request.getUser(), Constants.NEW_REQUEST);
        }

        requestDao.save(request);
    }

    @Override
    public void updateRequest(Request request) {
        requestDao.update(request);
    }

    @Override
    public void deleteRequest(int id) {
        requestDao.deleteById(id);
    }

    @Override
    public List<Request> getAllRequest() {
        return requestDao.getAll();
    }

    @Override
    public Request getRequestById(int id) {
        return requestDao.get(id);
    }

    @Override
    public List<Request> getPublicRequests() {
        return requestDao.getPublicRequests();
    }

    @Override
    public List<Request> getRequestsByUserFrom(int user_id) {
        return requestDao.getRequestsByUserFrom(user_id);
    }

    @Override
    public List<Request> getRequestsByUser(int user_id) {
        return requestDao.getRequestsByUser(user_id);
    }
}
