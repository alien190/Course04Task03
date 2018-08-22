package com.example.alien.course04task03.data.model;

import com.google.gson.annotations.SerializedName;

public class Token {
	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("scope")
	private String scope;

	@SerializedName("token_type")
	private String tokenType;

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setScope(String scope){
		this.scope = scope;
	}

	public String getScope(){
		return scope;
	}

	public void setTokenType(String tokenType){
		this.tokenType = tokenType;
	}

	public String getTokenType(){
		return tokenType;
	}

	@Override
 	public String toString(){
		return 
			"Token{" +
			"access_token = '" + accessToken + '\'' + 
			",scope = '" + scope + '\'' + 
			",token_type = '" + tokenType + '\'' + 
			"}";
		}
}
