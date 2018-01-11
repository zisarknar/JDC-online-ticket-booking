package com.solt.jdc.boot.domains;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Entity(name = "book")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
<<<<<<< HEAD
    
    @NotBlank(message="Registration Code cannot be empty")
=======
    @NotBlank(message = "Registration Code cannot be empty")
>>>>>>> feature/third-week-features
    private String regCode;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = ISO.DATE)
    @NotNull
    private Date bookDate;

    @NotNull
    private int noOfSeats;

    @NotNull
    @Min(value = 1)
    private double totalAmount;
    
    private boolean status;

    //  Booking is the Owner of the relationship
    @OneToOne(cascade = CascadeType.ALL)
    private Trip tripList;

    @OneToOne(cascade = CascadeType.ALL)
<<<<<<< HEAD
    @JoinColumn(name = "customer")
    private Customer customer;

    
    @Embedded
    private Passenger passenger;
    
    
    
    public Booking() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking Booking = (Booking) o;

        if (id != Booking.id) return false;
        if (noOfSeats != Booking.noOfSeats) return false;
        if (Double.compare(Booking.totalAmount, totalAmount) != 0) return false;
        if (status != Booking.status) return false;
        if (regCode != null ? !regCode.equals(Booking.regCode) : Booking.regCode != null) return false;
        return bookDate != null ? bookDate.equals(Booking.bookDate) : Booking.bookDate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (regCode != null ? regCode.hashCode() : 0);
        result = 31 * result + (bookDate != null ? bookDate.hashCode() : 0);
        result = 31 * result + noOfSeats;
        temp = Double.doubleToLongBits(totalAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status ? 1 : 0);
        return result;
     }

     @Override
     public String toString() {
        return "Booking{" +
                "id=" + id +
                ", regCode='" + regCode + '\'' +
                ", bookDate=" + bookDate +
                ", noOfSeats=" + noOfSeats +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }
}
=======
    private Customer customerList;

}
>>>>>>> feature/third-week-features
