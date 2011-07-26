package com.darko.feit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.darko.feit.form.Place;
import com.darko.feit.service.PlacesService;
import com.darko.feit.util.Constants;


@Controller
public class PlacesController {
 
	public static final String PLACES_ROOT = "/places";
	public static final String PLACES_INDEX = PLACES_ROOT+"/index";
	public static final String REDIRECT = "redirect:";
	public static final String PLACES_INDEX_REDIRECT = "redirect:"+Constants.ROOT+PLACES_INDEX;
	
    @Autowired
    private PlacesService placesService;
 
    @RequestMapping(PLACES_INDEX)
    public String listPlaces(Map<String, Object> map) {
 
        map.put("place", new Place());
        map.put("placesList", placesService.listPlaces());
 
        return "place";
    }
 
    @RequestMapping(value = PLACES_ROOT+"/add", method = RequestMethod.POST)
    public String addPlace(@ModelAttribute("place")
    Place place, BindingResult result) {
    	
        placesService.addPlace(place);
 
        return PLACES_INDEX_REDIRECT;
    }
 
    @RequestMapping(PLACES_ROOT+"/delete/{placeId}")
    public String deletePlace(@PathVariable("placeId")
    Integer placeId) {
 
        placesService.removePlace(placeId);
 
        return PLACES_INDEX_REDIRECT;
    }
}
