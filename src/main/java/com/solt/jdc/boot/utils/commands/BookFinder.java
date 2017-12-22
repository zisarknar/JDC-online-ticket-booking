package com.solt.jdc.boot.utils.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BookFinder {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fromDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date toDate;

}
