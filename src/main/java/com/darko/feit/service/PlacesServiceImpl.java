package com.darko.feit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darko.feit.dao.PlacesDAO;
import com.darko.feit.form.Place;

@Service
public class PlacesServiceImpl implements PlacesService {

	@Autowired
	private PlacesDAO placesDAO;

	@Transactional
	public void addPlace(Place place) {
		placesDAO.addPlace(place);
	}

	@Transactional
	public List<Place> listPlaces() {
		return placesDAO.listPlaces();
	}

	@Transactional
	public void removePlace(Integer id) {
		placesDAO.removePlace(id);
	}

	@Transactional
	public Place getPlace(Integer id) {
		return placesDAO.getPlace(id);
	}

}
