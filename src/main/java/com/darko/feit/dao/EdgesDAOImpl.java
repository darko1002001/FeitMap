package com.darko.feit.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.darko.feit.form.Edge;

@Repository
public class EdgesDAOImpl implements EdgesDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addEdge(Edge edge) {
		sessionFactory.getCurrentSession().save(edge);
	}

	@SuppressWarnings("unchecked")
	public List<Edge> listEdges() {
		return sessionFactory.getCurrentSession().createQuery("from Edge")
				.list();
	}

	public void removeEdge(Integer id) {
		Edge edge = (Edge) sessionFactory.getCurrentSession().load(
				Edge.class, id);
		if (null != edge) {
			sessionFactory.getCurrentSession().delete(edge);
		}
	}

}
