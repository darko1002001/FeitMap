package com.darko.feit.service;

import java.util.List;

import com.darko.feit.form.Place;

public interface PlacesService {
	public void addPlace(Place place);

	public List<Place> listPlaces();

	public void removePlace(Integer id);
}
