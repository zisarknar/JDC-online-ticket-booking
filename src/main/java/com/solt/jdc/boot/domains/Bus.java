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

@Entity
@Table(name = "bus")
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String busNumber;
	private int maxSeats;
	private int takenSeats;
	private String busCompany;
	private String busCode;

	@ManyToOne
	@JoinColumn(name = "bus_type_id")
	private BusType busType;

	@OneToMany(mappedBy = "bus")
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	public String toString() {
		return "Bus [id=" + id + ", busNumber=" + busNumber + ", maxSeats=" + maxSeats + ", takenSeats=" + takenSeats
				+ ", busCompany=" + busCompany + ", busCode=" + busCode + ", busType=" + busType + ", servicesList="
				+ servicesList + "]";
	}

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
		Bus other = (Bus) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
