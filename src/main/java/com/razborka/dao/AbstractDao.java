package com.razborka.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDao<T extends Object> implements Dao<T> {
	
	@Autowired
	private SessionFactory sessionFactory;
    private Class<T> domainClass;

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

    @SuppressWarnings("unchecked")
    private Class<T> getDomainClass() {
        if(domainClass == null) {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            this.domainClass = (Class<T>) type.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    private String getDomainClassName() {
        return getDomainClass().getName();
    }

    @Transactional
    public void save(T t) {
        getSession().save(t);
    }

    @Transactional
    public void update(T t) { getSession().update(t); }

    @Transactional
    public void deleteById(Serializable id) {
        getSession().delete(get(id));
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public T get(Serializable id) {
        return (T) getSession().get(getDomainClass(), id);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return getSession()
                .createQuery("from " + getDomainClassName())
                .list();
    }

    @Transactional
    public long count() {
        return (Long) getSession()
                .createQuery("select count(*) from " + getDomainClassName())
                .uniqueResult();
    }
}
