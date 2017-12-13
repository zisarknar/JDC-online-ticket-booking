package com.solt.jdc.boot.domains;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.Date;

@Entity(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message="Registration Code cannot be empty")
    private String regCode;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=ISO.DATE)
    @Future
    @NotNull
    private Date bookDate;
    
    @Min(value=20,message="No of seats must be atleast 20")
    private int noOfSeats;
    
    @NotNull
    @Min(value=1)
    private double totalAmount;
    private boolean status;

//    Booking is the Owner of the relationship
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip")
    private Trip trip;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer")
    private Customer customer;


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
