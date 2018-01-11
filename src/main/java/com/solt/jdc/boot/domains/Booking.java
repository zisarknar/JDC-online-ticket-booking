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

    @NotBlank(message = "Registration Code cannot be empty")
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
    private Customer customerList;

}
