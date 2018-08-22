package com.example.alien.course04task03.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RepCreationResponse{

	@SerializedName("message")
	private String message;

	@SerializedName("errors")
	private List<ErrorsItem> errors;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setErrors(List<ErrorsItem> errors){
		this.errors = errors;
	}

	public List<ErrorsItem> getErrors(){
		return errors;
	}

	@Override
 	public String toString(){
		return 
			"RepCreationResponse{" + 
			"message = '" + message + '\'' + 
			",errors = '" + errors + '\'' + 
			"}";
		}
}