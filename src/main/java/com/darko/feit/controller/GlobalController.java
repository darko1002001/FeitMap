package com.darko.feit.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.darko.feit.form.Edge;
import com.darko.feit.form.Place;
import com.darko.feit.service.EdgesService;
import com.darko.feit.service.PlacesService;

@Controller
public class GlobalController{

	@Autowired
	private PlacesService placesService;
	
	@Autowired
	private EdgesService edgesService;
	
	@RequestMapping("/homepage")
    public String homePage(Map<String, Object> map) {
		List<Place> listPlaces = placesService.listPlaces();
		JSONArray array = new JSONArray();
		try {
		for (Place place : listPlaces) {
			JSONObject obj = new JSONObject();
				obj.put("id", place.getId());
				obj.put("name", place.getName());
				System.out.println(obj.toString());
				JSONArray adjacencies = new JSONArray();
				Iterator<Edge> iter = place.getFromPlace().iterator();
				while (iter.hasNext()) {
					Edge edge = (Edge) iter.next();
					adjacencies.put(String.valueOf(edge.getToPlace().getId()));
					obj.put("adjacencies", adjacencies);
				}
				array.put(obj);
		}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		map.put("json", array);
		try {
			System.out.println(array.toString(1));
		} catch (JSONException e) {
			e.printStackTrace();
		}
        return "homepage";
    }
	
	@RequestMapping("/rest/sync")
    public void json(HttpServletResponse response) {
	
		JSONObject root = new JSONObject();
		JSONArray array = new JSONArray();
		
		try {
		for (Place place : placesService.listPlaces()) {
			JSONObject obj = new JSONObject();
				obj.put("id", place.getId());
				obj.put("name", place.getName());
				obj.put("imageUrl", place.getImageUrl());
				obj.put("description",place.getDescription());
				array.put(obj);
		}
		root.put("places", array);
		array = new JSONArray();
		for(Edge edge: edgesService.listEdges()){
			JSONObject obj = new JSONObject();
			obj.put("id", edge.getId());
			obj.put("fromPlace", edge.getFromPlace().getId());
			obj.put("toPlace",edge.getToPlace().getId());
			obj.put("cost",edge.getCost());
			array.put(obj);
		}
		root.put("edges", array);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			response.getWriter().write(root.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
