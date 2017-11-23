package com.solt.jdc.boot.domains;

public enum Role {
	ADIM("admin"),
	USER("user"),
	DRIVER("driver"),
	ASS_DRIVER("assistant driver");
	
	private final String displayRole;
	
	Role(String role) {
		this.displayRole=role;
	}
	
	public String getRoleName() {
		return displayRole;
	}	
}