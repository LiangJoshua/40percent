package com.forty_percent.config;

public enum UserPermission {
	API_USER_READ("api_user:read"),
	API_USER_WRITE("api_user:write");

	private final String permission;

	UserPermission(String permission){
		this.permission = permission;
	}

	public String getPermission(){
		return permission;
	}
}
