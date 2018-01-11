package com.solt.jdc.boot.domains;

import com.solt.jdc.boot.validators.customAnnotations.ValidEmail;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Data
public class User {

<<<<<<< HEAD
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
	@ManyToOne//moe
    private UserRole role_user;
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
=======
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
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotBlank(message = "Please enter phone")
    @Size(min = 3, max = 100)
    private String phone;

    @NotNull
    private boolean status = true;

    @NotNull
    private String role;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_station_id")
    private Station station;

>>>>>>> feature/third-week-features

	public UserRole getRole_user() {
		return role_user;
	}

	public void setRole_user(UserRole role_user) {
		this.role_user = role_user;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
	
}
