package com.darko.feit.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IMAGES")
public class Image {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="PATH")
	private String path;
	
	@Column(name="FILENAME")
	private String filename;
	
	@Column(name="CONTENT_TYPE")
	private String contentType;
	
	@Column(name="SIZE")
	private long size;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
