package com.solt.jdc.boot.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Services {

    @Id
    private int id;

    @NotBlank(message = "Please Enter Services Name")
    private String services;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;


}
