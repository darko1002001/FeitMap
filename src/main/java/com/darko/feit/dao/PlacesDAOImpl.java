package com.darko.feit.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.darko.feit.form.Edge;
import com.darko.feit.form.Place;

@Transactional
@Repository
public class PlacesDAOImpl implements PlacesDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addPlace(Place place) {
		sessionFactory.getCurrentSession().save(place);
	}

	@SuppressWarnings("unchecked")
	public List<Place> listPlaces() {
		return sessionFactory.getCurrentSession().createQuery("from Place")
				.list();
	}

	public void removePlace(Integer id) {
		Session currentSession = sessionFactory.openSession();
		Place place = (Place) currentSession.load(
				Place.class, id);
		if (null != place) {
			
			Transaction tx = null;
			try {
				tx = currentSession.getTransaction();
				tx.begin();
				Iterator<Edge> iter = place.getFromPlace().iterator();
				while (iter.hasNext()) {
					Edge edge = (Edge) iter.next();
					currentSession.delete(edge);
				}
				iter = place.getToPlace().iterator();
				while (iter.hasNext()) {
					Edge edge = (Edge) iter.next();
					currentSession.delete(edge);
				}
				currentSession.delete(place);
				tx.commit();
			} catch (HibernateException e) {
				if(tx!=null){
				tx.rollback();
				}
			}  finally { 
	           currentSession.close();
	      }
		}

	}

}