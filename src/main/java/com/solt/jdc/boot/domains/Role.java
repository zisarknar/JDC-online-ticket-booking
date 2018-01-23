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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    
    @OneToMany(mappedBy = "role")
    private List<User> users=new ArrayList<>();
    
    
}
