package com.solt.jdc.boot.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Station {
<<<<<<< HEAD
	@Id
	private int id;
	
	@NotBlank(message="Please enter name")
	private String name;
	
	@NotBlank(message="Please enter phoneNumber")
	private String phoneNumber;

	@OneToOne
	private Address address;

	@OneToMany(mappedBy="station")
	private List<Bus> busList=new ArrayList<>();
	
	@OneToMany(mappedBy="station")
	private List<User> userList=new ArrayList<>();
	
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
=======
    @Id
    private int id;
>>>>>>> feature/third-week-features

    @NotBlank(message = "Please enter name")
    private String name;

    @NotBlank(message = "Please enter phoneNumber")
    private String phoneNumber;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "bus")
    private List<Bus> busList = new ArrayList<>();

    @OneToMany(mappedBy = "station")
    private List<User> userList = new ArrayList<>();

}
