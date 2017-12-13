package com.solt.jdc.boot.domains;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name", nullable = false)
    @NotBlank(message = "User Name cannot be empty")
    @NotNull
    private String userName;

    @Size(min = 3, max = 100)
    @NotBlank(message = "Please Enter password")
    private String password;

    @NotNull
    @NotBlank(message = "Please Enter First Name")
    @Size(min = 3, max = 100)
    private String firstName;

    @NotNull
    @NotBlank(message = "Please Enter last name")
    @Size(min = 3, max = 100)
    private String lastName;

    @NotNull
    @Size(min = 3, max = 100)
    private String email;

    @NotNull
    @NotBlank(message = "Please enter phone")
    @Size(min = 3, max = 100)
    private String phone;

    //@Transient
    private boolean status = true;

    /*@Enumerated(EnumType.STRING)
    private Role role;*/
    //
	/*@Transient
	@Embedded
	private Role role;
	*/
    @NotNull
    private String role;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_station_id")
    private Station station;


}
