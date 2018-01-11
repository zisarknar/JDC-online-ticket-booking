package com.solt.jdc.boot.domains;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


import java.util.ArrayList;


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

    @NotBlank(message = "Registration Code cannot be empty")
    private String regCode;

    @Temporal(TemporalType.DATE)

    @DateTimeFormat(iso = ISO.DATE)
    @NotNull
    private Date bookDate;

    @Min(value = 1, message = "No of seats must be atleast 1")
    private int noOfSeats;

    private String seatNo;

    @NotNull
    @Min(value = 1)
    private double totalAmount;

    private boolean status;

    //    Booking is the Owner of the relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer")
    private Customer customer;

    @Embedded
    private Passenger passenger;


}
