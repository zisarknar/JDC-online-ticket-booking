package com.solt.jdc.boot.domains;

public enum Role {

    MANAGER("Manager"),
    STAFF("Staff");


    private final String displayRole;

    Role(String role) {
        this.displayRole = role;
    }

    public String getRoleName() {
        return displayRole;
    }
}

