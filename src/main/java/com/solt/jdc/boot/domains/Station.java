package com.solt.jdc.boot.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Station {
    @Id
    private int id;

    @NotBlank(message = "Please enter name")
    private String name;

    @NotBlank(message = "Please enter phoneNumber")
    private String phoneNumber;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "bus")
    private List<Bus> busList = new ArrayList<>();

    @OneToMany(mappedBy = "station")
    private List<User> userList = new ArrayList<>();

}
