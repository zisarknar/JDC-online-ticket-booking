package com.solt.jdc.boot.domains;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Entity

public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_name",nullable=false)
	@NotBlank(message="User Name cannot be empty")
	@NotNull
	private String userName;

	@Size(min=3,max=100)
	@NotBlank(message="Please Enter password")
	private String password;

	@NotNull
	@NotBlank(message="Please Enter First Name")
	@Size(min=3,max=100)
	private String firstName;

	@NotNull
	@NotBlank(message="Please Enter last name")
	@Size(min=3,max=100)
	private String lastName;

	@NotNull
	@Size(min=3,max=100)
	private String email;

	@NotNull
	@NotBlank(message="Please enter phone")
	@Size(min=3,max=100)
	private String phone;
	
	
	@NotNull
	private String role;

	//@Transient
	private boolean status=true;

	 
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="user_station_id")
	private Station station;
	
	/*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private Role role;
*/	
	
	
}
