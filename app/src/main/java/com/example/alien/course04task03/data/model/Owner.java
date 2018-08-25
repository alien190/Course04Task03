package com.example.alien.course04task03.data.model;

import com.google.gson.annotations.SerializedName;

public class Owner{

	@SerializedName("login")
	private String login;


	public void setLogin(String login){
		this.login = login;
	}

	public String getLogin(){
		return login;
	}


	@Override
 	public String toString(){
		return 
			"Owner{" + 
			",login = '" + login + '\'' +
			"}";
		}
}