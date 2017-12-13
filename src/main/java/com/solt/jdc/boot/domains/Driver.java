package com.solt.jdc.boot.domains;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        if (id != driver.id) return false;
        if (status != driver.status) return false;
        if (name != null ? !name.equals(driver.name) : driver.name != null) return false;
        if (code != null ? !code.equals(driver.code) : driver.code != null) return false;
        if (phone != null ? !phone.equals(driver.phone) : driver.phone != null) return false;
        if (email != null ? !email.equals(driver.email) : driver.email != null) return false;
        return driverRole != null ? driverRole.equals(driver.driverRole) : driver.driverRole == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (driverRole != null ? driverRole.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", driverRole='" + driverRole + '\'' +
                '}';
    }
}