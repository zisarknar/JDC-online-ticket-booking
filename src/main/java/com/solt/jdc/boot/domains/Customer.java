package com.solt.jdc.boot.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Please enter user name")
    private String username;
    @NotNull
    @NotBlank(message = "Please enter first name")
    private String firstName;
    @NotNull
    @NotBlank(message = "Please enter last name")
    private String lastName;
    @NotNull
    @NotBlank(message = "Please enter password")
    private String password;
    private String matchPassword;
    @NotNull
    @NotBlank(message = "Please enter phone number")
    private String phone;

    private String email;

    private String nrcNumber;
    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerList")
    private Booking booking;

}

