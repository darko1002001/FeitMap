package com.darko.feit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darko.feit.dao.EdgesDAO;
import com.darko.feit.form.Edge;

@Service
public class EdgesServiceImpl implements EdgesService {

	@Autowired
	private EdgesDAO edgesDAO;

	@Transactional
	public void addEdge(Edge edge) {
		edgesDAO.addEdge(edge);
	}

	@Transactional
	public List<Edge> listEdges() {
		return edgesDAO.listEdges();

	}

	@Transactional
	public void removeEdge(Integer id) {
		edgesDAO.removeEdge(id);
	}

}
