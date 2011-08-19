package com.darko.feit.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.darko.feit.form.Edge;
import com.darko.feit.form.Image;
import com.darko.feit.form.Place;
import com.darko.feit.service.EdgesService;
import com.darko.feit.service.ImagesService;
import com.darko.feit.service.PlacesService;
import com.darko.feit.util.Constants;

@Controller
public class GlobalController{


	@Autowired
	private PlacesService placesService;
	
	@Autowired
	private EdgesService edgesService;
	
	@Autowired
	private ImagesService imagesService;
	
	@RequestMapping("/homepage")
    public String homePage(Map<String, Object> map,HttpServletRequest request) {
		List<Place> listPlaces = placesService.listPlaces();
		JSONArray array = new JSONArray();
		 String baseUrl = String.format("%s://%s:%d"+request.getContextPath()+Constants.ROOT,request.getScheme(),request.getServerName(), request.getServerPort());
		try {
			JSONObject data;
			JSONObject obj;
			JSONArray adjacencies;

		for (Place place : listPlaces) {
				obj = new JSONObject();
				data = new JSONObject();
				adjacencies = new JSONArray();
				obj.put("id", place.getId());
				obj.put("name", place.getName());
				data.put("imageUrl", baseUrl+Constants.IMAGE_GET+place.getImageUrl());
				data.put("description", place.getDescription());
				obj.put("data", data);
			//	System.out.println(obj.toString());
				if(place.getFromPlace().size()==0)
				{
					continue;
				}
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
	/*	try {
			System.out.println(array.toString(1));
		} catch (JSONException e) {
			e.printStackTrace();
		}*/
        return "homepage";
    }
	
	@RequestMapping("/rest/sync")
    public void json(HttpServletResponse response,HttpServletRequest request) {
		JSONObject root = new JSONObject();
		JSONArray array = new JSONArray();
		 String baseUrl = String.format("%s://%s:%d"+request.getContextPath()+Constants.ROOT,request.getScheme(),request.getServerName(), request.getServerPort());
		try {
		for (Place place : placesService.listPlaces()) {
			JSONObject obj = new JSONObject();
				obj.put("id", place.getId());
				obj.put("name", place.getName());
				obj.put("imageUrl",baseUrl+Constants.IMAGE_GET+place.getImageUrl());
				obj.put("isValidDestination",place.isValidDestination());
				obj.put("description",place.getDescription()+"");
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

	@RequestMapping(value = {Constants.IMAGE_GET + "{imageId}",PlacesController.PLACES_ROOT+Constants.IMAGE_GET + "{imageId}"})
	public void getImage(HttpServletResponse response,
			@PathVariable("imageId") Integer imageId) {
		
		File imageFile = null;
		Image image = imagesService.getImage(imageId);
		if(image==null){
			return;
		}
		System.out.println(image.getPath());
		File dir = new File(Constants.IMAGE_DIRECTORY);
		imageFile = new File(dir,image.getPath());
		
	/*	String[] children = dir.list();
		if (children == null) {
		    // Either dir does not exist or is not a directory
		} else {
		    for (int i=0; i<children.length; i++) {
		        // Get filename of file or directory
		    	System.out.println(children[i]);
		    	imageFile = new File(dir,children[i]);
		      //  String filename = children[i];
		    }
		}*/
		
		try {

			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(imageFile));
			int c;
			while ((c = bis.read()) > -1) {
				response.getOutputStream().write(c);
			}
			bis.close();
			return;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
