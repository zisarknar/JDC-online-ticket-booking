package com.solt.jdc.boot.domains;

import lombok.Data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String role;
    @OneToMany(mappedBy = "role_user", cascade = CascadeType.ALL)
    private List<Customer> customers;
    @OneToMany(mappedBy = "role_user", cascade = CascadeType.ALL)
    private List<User> users;


}
