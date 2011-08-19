package com.darko.feit.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.darko.feit.form.Image;

@Repository
public class ImageDAOImpl implements ImageDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addImage(Image image) {
		return (Integer) sessionFactory.getCurrentSession().save(image);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> listImages() {
		return sessionFactory.getCurrentSession().createQuery("from Image")
				.list();
	}

	@Override
	public void removeImage(Integer id) {
		Image image= (Image) sessionFactory.getCurrentSession().load(
				Image.class, id);
		if (null != image) {
			sessionFactory.getCurrentSession().delete(image);
		}
	}

	@Override
	public Image getImage(Integer id) {
		return (Image) sessionFactory.getCurrentSession().get(
				Image.class, id);
	}

}
