package com.darko.feit.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
 
    @RequestMapping(value = {PLACES_ROOT+"/add/persist", PLACES_ROOT+"/persist" } ,method = RequestMethod.POST)
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
    
    @RequestMapping(value = PLACES_ROOT+"/add/{placeId}", method = RequestMethod.GET)
    public ModelAndView updateExistingPlace(final Model model, @PathVariable("placeId")
    Integer placeId,final HttpServletRequest request, final HttpServletResponse response) {
    	
    	
        Place place = placesService.getPlace(placeId);
		model.addAttribute("place", place);
       
        return new ModelAndView("place_add");
	}
    
    @RequestMapping(value = PLACES_ROOT+"/add", method = RequestMethod.GET)
    public ModelAndView addNewPlace(final Model model, final HttpServletRequest request, final HttpServletResponse response) {
    	
        model.addAttribute("place", new Place());
       
        return new ModelAndView("place_add");
	}
    
    @RequestMapping(value = {PLACES_ROOT+"/add/images", PLACES_ROOT+"/images" } ,method = RequestMethod.GET)
    public @ResponseBody String getImages() {
    	List<Place> listPlaces = placesService.listPlaces();
    	JSONArray array= new JSONArray();
    	
    	for (Place place : listPlaces) {
    		array.put(place.getImageUrl());
		}
    	
        return array.toString();
    }
}
