package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.NewsDao;
import com.razborka.model.News;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 14.05.2015.
 */
@Repository("newsDao")
public class NewsDaoImpl extends AbstractDao<News> implements NewsDao {
    public List<News> getAllPublicNews() {
        return getSession().createCriteria(News.class)
                .add(Restrictions.eq("pub", true))
                .list();
    }
}
