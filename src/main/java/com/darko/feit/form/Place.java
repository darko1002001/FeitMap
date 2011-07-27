package com.darko.feit.form;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name="PLACES")
public class Place {
	
	
		@Id
	    @Column(name="ID")
	    @GeneratedValue
	    private Integer id;
	 
	    @Column(name="NAME")
	    private String name;
	 
	    @Column(name="DESCRIPTION")
	    private String description;
	 
	    @Column(name="IMAGEURL")
	    private String imageUrl;
	 
	    @Column(name="ISVALIDDESTINATION")
	    @Type(type = "org.hibernate.type.NumericBooleanType")
	    private boolean validDestination;
	    
	    @OneToMany(fetch=FetchType.EAGER)
		@JoinColumn(name = "FROMPLACE_ID")
		private Set<Edge> fromPlace = new HashSet<Edge>(0);
	    
	    @OneToMany(fetch=FetchType.EAGER)
		@JoinColumn(name = "TOPLACE_ID")
		private Set<Edge> toPlace = new HashSet<Edge>(0);
	    
		public Set<Edge> getFromPlace() {
			return fromPlace;
		}
		public void setFromPlace(Set<Edge> fromPlace) {
			this.fromPlace = fromPlace;
		}
		public Set<Edge> getToPlace() {
			return toPlace;
		}
		public void setToPlace(Set<Edge> toPlace) {
			this.toPlace = toPlace;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getImageUrl() {
			return imageUrl;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public boolean isValidDestination() {
			return validDestination;
		}
		public void setValidDestination(boolean isValidDestination) {
			this.validDestination = isValidDestination;
		}
		public Integer getId() {
	        return id;
	    }
	    public void setId(Integer id) {
	        this.id = id;
	    }
}
