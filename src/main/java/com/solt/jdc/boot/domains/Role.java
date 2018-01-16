package com.solt.jdc.boot.domains;
/*
public enum Role {

    ROLE_MANAGER("Manager"),
    ROLE_STAFF("Staff"),
    ROLE_CUSTOMER("Customer"),
    ROLE_ROOT("Root");


    private final String displayRole;

    Role(String role) {
        this.displayRole = role;
    }

    public String getRoleName() {
        return displayRole;
    }

}
*/

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
