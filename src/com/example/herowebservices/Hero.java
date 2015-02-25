package com.example.herowebservices;

public class Hero {
	
	private String name;
	private String description;
	private String imagepath;
	
	public Hero(String name, String description, String imagepath) {
		super();
		this.name = name;
		this.description = description;
		this.imagepath = imagepath;
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

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
}

