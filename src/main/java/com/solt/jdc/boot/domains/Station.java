package com.solt.jdc.boot.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Station {
	@Id
	private int id;
	
	@NotBlank(message="Please enter name")
	private String name;
	
	@NotBlank(message="Please enter phoneNumber")
	private String phoneNumber;

	@OneToOne
	private Address address;

	@OneToMany(mappedBy="bus")
	private List<Bus> busList=new ArrayList<>();
	
	@OneToMany(mappedBy="station")
	private List<User> userList=new ArrayList<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Station [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}

}
