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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;



@Entity
@Getter
@Setter
@NoArgsConstructor
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (status != user.status) return false;
		if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
		if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
		if (email != null ? !email.equals(user.email) : user.email != null) return false;
		if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
		if (role != null ? !role.equals(user.role) : user.role != null) return false;
		return station != null ? station.equals(user.station) : user.station == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (userName != null ? userName.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (phone != null ? phone.hashCode() : 0);
		result = 31 * result + (status ? 1 : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		result = 31 * result + (station != null ? station.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", status=" + status +
				", role='" + role + '\'' +
				", station=" + station +
				'}';
	}
}
