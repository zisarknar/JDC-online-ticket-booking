package com.solt.jdc.boot.domains;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

<<<<<<< HEAD
import org.hibernate.validator.constraints.Email;
=======
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
>>>>>>> feature/third-week-features
import org.hibernate.validator.constraints.NotBlank;

import com.solt.jdc.boot.validator.CharacterConstraint;
import com.solt.jdc.boot.validator.ContactNumberConstraint;

@Entity(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
<<<<<<< HEAD
    @Size(min=4)
    private String username;
    @CharacterConstraint(message="Your's frist name should be only character")//moe
    private String firstName;
    @CharacterConstraint(message="Your's last name should be only character")//moe
    private String lastName;
    @NotNull
    @NotBlank(message="Please enter password")
    @Size(min=8)
=======
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
>>>>>>> feature/third-week-features
    private String password;
    @Transient
    private String matchPassword;
<<<<<<< HEAD
    @ContactNumberConstraint
    private String phone;
    @ManyToOne//moe
    private UserRole role_user;
    @NotNull
    @NotBlank(message="Please enter  your address ")
    private String address;
  
    @Email(message="This is not email type")  
=======
    @NotNull
    @NotBlank(message = "Please enter phone number")
    private String phone;

>>>>>>> feature/third-week-features
    private String email;

    private String nrcNumber;
    
    @Column(name = "enabled")
    private boolean enabled;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customerList")
    private Booking booking;
<<<<<<< HEAD
    @Transient
    private String tempPassword;
    
    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getNrcNumber() {
        return nrcNumber;
    }

    public void setNrcNumber(String nrcNumber) {
        this.nrcNumber = nrcNumber;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getMatchPassword() {
        return matchPassword;
    }

    public void setMatchPassword(String matchPassword) {
        this.matchPassword = matchPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserRole getRole_user() {
		return role_user;
	}

	public void setRole_user(UserRole role_user) {
		this.role_user = role_user;
	}

	public String getTempPassword() {
		return tempPassword;
	}

	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}

    
    
    
=======

>>>>>>> feature/third-week-features
}

