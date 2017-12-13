package com.solt.jdc.boot.domains;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "book")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Registration Code cannot be empty")
    private String regCode;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = ISO.DATE)
    @Future
    @NotNull
    private Date bookDate;

    @Min(value = 20, message = "No of seats must be atleast 20")
    private int noOfSeats;

    @NotNull
    @Min(value = 1)
    private double totalAmount;
    private boolean status;

    //  Booking is the Owner of the relationship
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip")
    private Trip trip;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer")
    private Customer customer;

}
