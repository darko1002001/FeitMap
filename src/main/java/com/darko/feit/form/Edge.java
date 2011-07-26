package com.darko.feit.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EDGES")
public class Edge {

	public Edge() {
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="FROMPLACE_ID")
	private Place fromPlace;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="TOPLACE_ID")
	private Place toPlace;
	
	@Column(name="COST")
	private Integer cost;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Place getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(Place fromPlace) {
		this.fromPlace = fromPlace;
	}

	public Place getToPlace() {
		return toPlace;
	}

	public void setToPlace(Place toPlace) {
		this.toPlace = toPlace;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}
}
