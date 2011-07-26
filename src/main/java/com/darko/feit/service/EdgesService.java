package com.darko.feit.service;

import java.util.List;

import com.darko.feit.form.Edge;

public interface EdgesService {
	public void addEdge(Edge edge);

	public List<Edge> listEdges();

	public void removeEdge(Integer id);
}
