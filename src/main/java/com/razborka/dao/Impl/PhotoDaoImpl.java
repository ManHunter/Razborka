package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.PhotoDao;
import com.razborka.model.Body;
import com.razborka.model.Photo;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Repository("photoDao")
public class PhotoDaoImpl extends AbstractDao<Photo> implements PhotoDao {

    public Photo getPhotoByImageName(String imageName) {
        Photo photo = (Photo) getSession().createCriteria(Photo.class)
                .add(Restrictions.eq("picture", imageName))
                .setMaxResults(1)
                .uniqueResult();
        return photo;
    }

    public List<Photo> getAllPhotoByPartId(int part_id) {
        return getSession().createCriteria(Photo.class)
                .add(Restrictions.eq("part.id", part_id))
                .list();
    }
}
