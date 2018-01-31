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

<<<<<<< HEAD
import lombok.Data;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> feature/14-Jan-2018(htein)

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
<<<<<<< HEAD
=======

>>>>>>> feature/14-Jan-2018(htein)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
<<<<<<< HEAD
=======
    
    
    @OneToMany(mappedBy = "role")
    private List<User> users=new ArrayList<>();
    
    
>>>>>>> feature/14-Jan-2018(htein)
}
