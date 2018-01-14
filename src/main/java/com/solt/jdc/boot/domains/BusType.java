package com.solt.jdc.boot.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bus_type")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BusType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Please enter bus type")
    private String type;
    @JsonIgnore
    @OneToMany(mappedBy = "busType",cascade=CascadeType.ALL)
    private List<Bus> busList = new ArrayList<>();

}
