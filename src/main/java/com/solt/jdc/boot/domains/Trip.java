package com.solt.jdc.boot.domains;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author User
 */
@Entity(name = "trip")
@Data
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Please Enter Trip Code")
    private String tripCode;

    @DateTimeFormat(iso = ISO.TIME)
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

    @OneToOne(mappedBy = "tripList")
    private Booking booking;

    private String source;

    private String destination;


}
