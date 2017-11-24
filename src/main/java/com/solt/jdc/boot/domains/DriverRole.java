package com.solt.jdc.boot.domains;

public enum DriverRole {
	DRIVER("driver"),
	ASS_DRIVER("assistant driver");
	
	private final String displayRole;
	
	DriverRole(String role) {
		this.displayRole=role;
	}
	
	public String getRoleName() {
		return displayRole;
	}
}
