package com.darko.feit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darko.feit.dao.ImageDAO;
import com.darko.feit.form.Image;

@Service
public class ImagesServiceImpl implements ImagesService{

	@Autowired
	private ImageDAO imagesDAO;

	
	@Override
	@Transactional
	public Integer addImage(Image image) {
		return imagesDAO.addImage(image);
	}

	@Override
	@Transactional
	public List<Image> listImages() {
		return imagesDAO.listImages();
	}

	@Override
	@Transactional
	public void removeImage(Integer id) {
		imagesDAO.removeImage(id);
	}

	@Override
	@Transactional
	public Image getImage(Integer id) {
		return imagesDAO.getImage(id);
	}

}
