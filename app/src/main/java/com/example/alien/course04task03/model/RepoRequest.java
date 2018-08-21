package com.example.alien.course04task03.model;

import com.google.gson.annotations.SerializedName;

public class RepoRequest {
	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("homepage")
	private String homepage;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setHomepage(String homepage){
		this.homepage = homepage;
	}

	public String getHomepage(){
		return homepage;
	}

	@Override
 	public String toString(){
		return 
			"RepoRequest{" +
			"name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",homepage = '" + homepage + '\'' + 
			"}";
		}
}
