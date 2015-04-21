package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.PartGroupDao;
import com.razborka.model.Body;
import com.razborka.model.PartGroup;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Repository("partGroupDao")
public class PartGroupDaoImpl extends AbstractDao<PartGroup> implements PartGroupDao {

}
