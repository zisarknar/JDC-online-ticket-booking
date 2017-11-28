package com.solt.jdc.boot.domains;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity (name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tripCode;

    @Temporal(TemporalType.TIME)
    private Date depTime;

    @Temporal(TemporalType.TIME)
    private Date estTime;

    private double unitPrice;

    private int busId;

    private int stationId;

    private boolean status;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "trip")
    private Booking booking;
    
    private String source;
    private String destination;
    
    public Trip() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTripCode() {
        return tripCode;
    }

    public void setTripCode(String tripCode) {
        this.tripCode = tripCode;
    }

    public Date getDepTime() {
        return depTime;
    }

    public void setDepTime(Date depTime) {
        this.depTime = depTime;
    }

    public Date getEstTime() {
        return estTime;
    }

    public void setEstTime(Date estTime) {
        this.estTime = estTime;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    
    

  
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;

        if (id != trip.id) return false;
        if (Double.compare(trip.unitPrice, unitPrice) != 0) return false;
        if (busId != trip.busId) return false;
        if (stationId != trip.stationId) return false;
        if (status != trip.status) return false;
        if (tripCode != null ? !tripCode.equals(trip.tripCode) : trip.tripCode != null) return false;
        if (depTime != null ? !depTime.equals(trip.depTime) : trip.depTime != null) return false;
        if (estTime != null ? !estTime.equals(trip.estTime) : trip.estTime != null) return false;
        return booking != null ? booking.equals(trip.booking) : trip.booking == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (tripCode != null ? tripCode.hashCode() : 0);
        result = 31 * result + (depTime != null ? depTime.hashCode() : 0);
        result = 31 * result + (estTime != null ? estTime.hashCode() : 0);
        temp = Double.doubleToLongBits(unitPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + busId;
        result = 31 * result + stationId;
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (booking != null ? booking.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", tripCode='" + tripCode + '\'' +
                ", depTime=" + depTime +
                ", estTime=" + estTime +
                ", unitPrice=" + unitPrice +
                ", busId=" + busId +
                ", stationId=" + stationId +
                '}';
    }
    
}
