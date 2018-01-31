package com.solt.jdc.boot.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Data
@NoArgsConstructor
public class Passenger {

    private String name;

    private String regNo;

    @Size(min = 5, max = 20)
    private String phone;

    private String specialDesc;

    public Passenger(String name, String phone, String specialDesc) {
        super();
        this.name = name;
        this.phone = phone;
        this.specialDesc = specialDesc;
    }
}