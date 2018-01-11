package com.solt.jdc.boot.domains;

public enum Role {
<<<<<<< HEAD
	
	ROLE_MANAGER("Manager"),
	ROLE_STAFF("Staff"),
	ROLE_CUSTOMER("Customer"),
	ROLE_ROOT("Root");
	
	
	private final String displayRole;
	
	Role(String role) {
		this.displayRole=role;
	}
	
	public String getRoleName() {
		return displayRole;
	}	
=======

    MANAGER("Manager"),
    STAFF("Staff");


    private final String displayRole;

    Role(String role) {
        this.displayRole = role;
    }

    public String getRoleName() {
        return displayRole;
    }
>>>>>>> feature/third-week-features
}

