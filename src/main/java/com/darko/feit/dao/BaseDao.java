package com.darko.feit.dao;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDao extends HibernateDaoSupport {

    @Autowired SessionFactory sessionFactory;

    @SuppressWarnings("unused")
    @PostConstruct
    private void initSessionFactory() {
        setSessionFactory(sessionFactory);
    }
}
