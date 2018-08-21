package com.example.alien.course04task03.model;

import com.google.gson.annotations.SerializedName;

public class ErrorsItem{

	@SerializedName("code")
	private String code;

	@SerializedName("field")
	private String field;

	@SerializedName("resource")
	private String resource;

	@SerializedName("message")
	private String message;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setField(String field){
		this.field = field;
	}

	public String getField(){
		return field;
	}

	public void setResource(String resource){
		this.resource = resource;
	}

	public String getResource(){
		return resource;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"ErrorsItem{" + 
			"code = '" + code + '\'' + 
			",field = '" + field + '\'' + 
			",resource = '" + resource + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}