package com.darko.feit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.darko.feit.form.Edge;
import com.darko.feit.form.Place;
import com.darko.feit.service.EdgesService;
import com.darko.feit.service.PlacesService;
import com.darko.feit.util.Constants;
import com.darko.feit.validators.EdgesValidator;

@Controller
public class EdgesController {

	private static final String EDGES_ROOT = "/edges";
	private static final String EDGES_INDEX = EDGES_ROOT + "/index";
	private static final String EDGES_REDIRECT_INDEX = "redirect:"
			+ Constants.ROOT + EDGES_INDEX;
	@Autowired
	private EdgesService edgesService;

	@Autowired
	private EdgesValidator validator;
	
	@Autowired
	private PlacesService placesService;

	@RequestMapping(EDGES_INDEX)
	public String listEdges(Map<String, Object> map) {

		map.put("edge", new Edge());
		map.put("edgesList", edgesService.listEdges());
		map.put("place", new Place());
		map.put("placesList", placesService.listPlaces());
		return "edge";
	}

	@RequestMapping(value = EDGES_ROOT + "/add", method = RequestMethod.POST)
	public String addEdge(@ModelAttribute("edge") Edge edge,
			BindingResult result) {
		validator.validate(edge, result);
		if(result.hasErrors())
		{
			return EDGES_REDIRECT_INDEX;
		}
		edgesService.addEdge(edge);

		return EDGES_REDIRECT_INDEX;
	}

	@RequestMapping(EDGES_ROOT + "/delete/{edgeId}")
	public String deleteEdge(@PathVariable("edgeId") Integer edgeId) {
		edgesService.removeEdge(edgeId);
		return EDGES_REDIRECT_INDEX;
	}
}
