package com.solt.jdc.boot.domains;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank(message = "Please enter name")
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @NotBlank(message = "Please enter Driver Code")
    @Size(min = 3, max = 100)
    private String code;

    @NotNull
    @NotBlank(message = "Please enter phone number")
    @Size(min = 3, max = 100)
    private String phone;

    @NotNull
    @Size(min = 3, max = 100)
    private String email;


    //@Transient
    private boolean status = true;

	/*@Enumerated(EnumType.STRING)
	private DriverRole driverRole;
	*/

    private String driverRole;


}