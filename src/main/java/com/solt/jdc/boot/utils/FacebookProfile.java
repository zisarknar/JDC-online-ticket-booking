package com.solt.jdc.boot.utils;



import lombok.Data;

@Data
public class FacebookProfile {
	private long id;
	private String name;

	private String password;
	private String first_name;
	private String last_name;
	private String email;
	private String phone;
	private String gender;
	private String birthday;
	private String location;
	private String hometown;
	
	
}
