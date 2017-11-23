package com.solt.jdc.boot.domains;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Driver {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Size(min=3,max=100)
	private  String name;
	

	@NotNull
	@Size(min=3,max=100)
	private  String code;
	

	@NotNull
	@Size(min=3,max=100)
	private String phone;

	@NotNull
	@Size(min=3,max=100)
	private String  email;
	

	@Transient
	private boolean status;
	
	@Enumerated(EnumType.STRING)
	private Role driverRole;
	
	
	public Driver() {
		
	}

	public Driver(String name, String code, String phone, String email, boolean status, Role driverRole) {
		this.name = name;
		this.code = code;
		this.phone = phone;
		this.email = email;
		this.status = status;
		this.driverRole = driverRole;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}

	public Role getDriverRole() {
		return driverRole;
	}

	public void setDriverRole(Role driverRole) {
		this.driverRole = driverRole;
	}
	
	
}