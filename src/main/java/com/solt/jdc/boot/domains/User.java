package com.solt.jdc.boot.domains;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class User {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	//@Column(name="user_name",nullable=false)
	
	//@NotBlank(message="User Name cannot be empty")
	//@NotNull
	//@Size(min=3,max=100)
	private String userName;

	@NotNull
	//@Size(min=3,max=100)
	private String password;

	@NotNull
	//@Size(min=3,max=100)
	private String firstName;

	@NotNull
	//@Size(min=3,max=100)
	private String lastName;

	@NotNull
	@Size(min=3,max=100)
	private String email;

	@NotNull
	@Size(min=3,max=100)
	private String phone;

	//@Transient
	private boolean status=true;
	
	/*@Enumerated(EnumType.STRING)
	private Role role;*/
	//
	/*@Transient
	@Embedded
	private Role role;
	*/
	@NotNull
	private String role; 
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="user_station_id")
	private Station station;
	public User() {
		
	}



	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}




	public String getRole() {
		return role;
	}




	public void setRole(String role) {
		this.role = role;
	}


	
	
	
//==========================
	/*
	public enum Role{

		ADIM("admin"),
		USER("user");
		
		
		private final String displayRole;
		
		Role(String role) {
			this.displayRole=role;
		}
		
		public String getRoleName() {
			return displayRole;
		}	
	}*/
	
	//===================================

	

	
		
}
