package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.BrandDao;
import com.razborka.model.Brand;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Repository("brandDao")
public class BrandDaoImpl extends AbstractDao<Brand> implements BrandDao {

}
