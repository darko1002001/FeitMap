package com.darko.feit.service;

import java.util.List;

import com.darko.feit.form.Image;

public interface ImagesService {
	
	 public Integer addImage(Image image);
	    public List<Image> listImages();
	    public void removeImage(Integer id);
	    public Image getImage(Integer id);
}
