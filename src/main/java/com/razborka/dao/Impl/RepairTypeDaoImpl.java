package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.RepairTypeDao;
import com.razborka.model.RepairType;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("repairTypeDao")
public class RepairTypeDaoImpl extends AbstractDao<RepairType> implements RepairTypeDao {

}
