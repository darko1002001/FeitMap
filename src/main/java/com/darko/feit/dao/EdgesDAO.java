package com.darko.feit.dao;

import java.util.List;

import com.darko.feit.form.Edge;

public interface EdgesDAO {
	 public void addEdge(Edge edge);
	    public List<Edge> listEdges();
	    public void removeEdge(Integer id);
}
