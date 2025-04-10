package com.exam.entity.exam;


import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long cid;
	public long getCid() {
		return cid;
	}


	public void setCid(long cid) {
		this.cid = cid;
	}


	private String title;
	private String description;
	
	
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL)
	private Set<Quiz> quizes =new LinkedHashSet<Quiz>();
	
	
	
	public Category( String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	
	public Category() {
		
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
