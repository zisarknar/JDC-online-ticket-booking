package com.solt.jdc.boot.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "bus")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Bus {
	@Id
	
	private Integer id;
	
	@NotBlank(message="Bus Number cannot be blank")
	private String busNumber;
	
	@Min(value=20,message="Maximum seats must be atleast 20 seats")
	private int maxSeats;
	
	
	private int takenSeats=0;
	@NotBlank(message="Please enter name of the bus company")
	private String busCompany;
	@NotBlank(message="Please enter bus code")
	private String busCode;

	@ManyToOne
	@JoinColumn(name = "bus_type_id")
	private BusType busType;

	@OneToMany(mappedBy = "bus")
	@JsonIgnore
	private List<Services> servicesList = new ArrayList<>();

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="bus_station_id")
	private Bus bus;
	
	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public BusType getBusType() {
		return busType;
	}

	public void setBusType(BusType busType) {
		this.busType = busType;
	}

	public List<Services> getServicesList() {
		return servicesList;
	}

	public void setServicesList(List<Services> servicesList) {
		this.servicesList = servicesList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(int maxSeats) {
		this.maxSeats = maxSeats;
	}

	public int getTakenSeats() {
		return takenSeats;
	}

	public void setTakenSeats(int takenSeats) {
		this.takenSeats = takenSeats;
	}

	public String getBusCompany() {
		return busCompany;
	}

	public void setBusCompany(String busCompany) {
		this.busCompany = busCompany;
	}

	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((busCode == null) ? 0 : busCode.hashCode());
		result = prime * result + ((busCompany == null) ? 0 : busCompany.hashCode());
		result = prime * result + ((busNumber == null) ? 0 : busNumber.hashCode());
		result = prime * result + ((busType == null) ? 0 : busType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + maxSeats;
		result = prime * result + ((servicesList == null) ? 0 : servicesList.hashCode());
		result = prime * result + takenSeats;
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
		Bus other = (Bus) obj;
		if (busCode == null) {
			if (other.busCode != null)
				return false;
		} else if (!busCode.equals(other.busCode))
			return false;
		if (busCompany == null) {
			if (other.busCompany != null)
				return false;
		} else if (!busCompany.equals(other.busCompany))
			return false;
		if (busNumber == null) {
			if (other.busNumber != null)
				return false;
		} else if (!busNumber.equals(other.busNumber))
			return false;
		if (busType == null) {
			if (other.busType != null)
				return false;
		} else if (!busType.equals(other.busType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maxSeats != other.maxSeats)
			return false;
		if (servicesList == null) {
			if (other.servicesList != null)
				return false;
		} else if (!servicesList.equals(other.servicesList))
			return false;
		if (takenSeats != other.takenSeats)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bus [id=" + id + ", busNumber=" + busNumber + ", maxSeats=" + maxSeats + ", takenSeats=" + takenSeats
				+ ", busCompany=" + busCompany + ", busCode=" + busCode + ", busType=" + busType + ", servicesList="
				+ servicesList + "]";
	}

	

	

}
