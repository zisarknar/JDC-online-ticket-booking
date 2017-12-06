package com.solt.jdc.boot.domains;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author User
 */
@Entity(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Please Enter Trip Code")
    private String tripCode;

    private LocalTime depTime;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = ISO.DATE)
    @Future(message = "Departure date cannot be in the past")
    private Date depDate;

    @Min(value = 1, message = "Bus trip must be atleast an hour long")
    private int estTime;

    @Min(value = 1, message = "Unit Price cannot be a negative value")
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

    public LocalTime getDepTime() {
        return depTime;
    }

    public void setDepTime(LocalTime depTime) {
        this.depTime = depTime;
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

    public Date getDepDate() {
        return depDate;
    }

    public void setDepDate(Date depDate) {
        this.depDate = depDate;
    }

    public void setEstTime(int estTime) {
        this.estTime = estTime;
    }

    public int getEstTime() {
        return estTime;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((booking == null) ? 0 : booking.hashCode());
        result = prime * result + busId;
        result = prime * result + ((depDate == null) ? 0 : depDate.hashCode());
        result = prime * result + ((depTime == null) ? 0 : depTime.hashCode());
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + estTime;
        result = prime * result + id;
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + stationId;
        result = prime * result + (status ? 1231 : 1237);
        result = prime * result + ((tripCode == null) ? 0 : tripCode.hashCode());
        long temp;
        temp = Double.doubleToLongBits(unitPrice);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Trip other = (Trip) obj;
        if (booking == null) {
            if (other.booking != null)
                return false;
        } else if (!booking.equals(other.booking))
            return false;
        if (busId != other.busId)
            return false;
        if (depDate == null) {
            if (other.depDate != null)
                return false;
        } else if (!depDate.equals(other.depDate))
            return false;
        if (depTime == null) {
            if (other.depTime != null)
                return false;
        } else if (!depTime.equals(other.depTime))
            return false;
        if (destination == null) {
            if (other.destination != null)
                return false;
        } else if (!destination.equals(other.destination))
            return false;
        if (estTime != other.estTime)
            return false;
        if (id != other.id)
            return false;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (stationId != other.stationId)
            return false;
        if (status != other.status)
            return false;
        if (tripCode == null) {
            if (other.tripCode != null)
                return false;
        } else if (!tripCode.equals(other.tripCode))
            return false;
        if (Double.doubleToLongBits(unitPrice) != Double.doubleToLongBits(other.unitPrice))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Trip [id=" + id + ", tripCode=" + tripCode + ", depTime=" + depTime + ", depDate=" + depDate
                + ", estTime=" + estTime + ", unitPrice=" + unitPrice + ", busId=" + busId + ", stationId=" + stationId
                + ", status=" + status + ", booking=" + booking + ", source=" + source + ", destination=" + destination
                + "]";
    }


}
