package com.darko.feit.dao;

import java.util.List;

import com.darko.feit.form.Place;

public interface PlacesDAO {
	 public void addPlace(Place place);
	    public List<Place> listPlaces();
	    public void removePlace(Integer id);
	    public Place getPlace(Integer id);
}
