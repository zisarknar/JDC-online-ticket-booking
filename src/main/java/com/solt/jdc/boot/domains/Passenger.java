package com.solt.jdc.boot.domains;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class Passenger {

	
	private String name;
	
	private String regNo;
	
	@Size(min=5,max=20)
	private String phone;
	
	private  String specialDesc;

	
	public Passenger() {
		
	}
//==============================================
	

	public Passenger(String name, String phone, String specialDesc) {
		super();
		this.name = name;
		this.phone = phone;
		this.specialDesc = specialDesc;
	}



	
	
//================**************=================
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getSpecialDesc() {
		return specialDesc;
	}


	public void setSpecialDesc(String specialDesc) {
		this.specialDesc = specialDesc;
	}


	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}	
}