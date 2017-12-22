package com.solt.jdc.boot.utils.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TripFinder {

    private String source;

    private String destination;

    @DateTimeFormat(iso = ISO.DATE)
    private Date depDate;

}
