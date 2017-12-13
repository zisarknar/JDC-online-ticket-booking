package com.solt.jdc.boot.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Please enter user name")
    private String username;
    @NotNull
    @NotBlank(message = "Please enter first name")
    private String firstName;
    @NotNull
    @NotBlank(message = "Please enter last name")
    private String lastName;
    @NotNull
    @NotBlank(message = "Please enter password")
    private String password;
    private String matchPassword;
    @NotNull
    @NotBlank(message = "Please enter phone number")
    private String phone;

    private String email;

    private String nrcNumber;
    @Column(name = "enabled")
    private boolean enabled;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    private Booking booking;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (enabled != customer.enabled) return false;
        if (username != null ? !username.equals(customer.username) : customer.username != null) return false;
        if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null) return false;
        if (lastName != null ? !lastName.equals(customer.lastName) : customer.lastName != null) return false;
        if (password != null ? !password.equals(customer.password) : customer.password != null) return false;
        if (matchPassword != null ? !matchPassword.equals(customer.matchPassword) : customer.matchPassword != null)
            return false;
        if (phone != null ? !phone.equals(customer.phone) : customer.phone != null) return false;
        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
        if (nrcNumber != null ? !nrcNumber.equals(customer.nrcNumber) : customer.nrcNumber != null) return false;
        return booking != null ? booking.equals(customer.booking) : customer.booking == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (matchPassword != null ? matchPassword.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (nrcNumber != null ? nrcNumber.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (booking != null ? booking.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", matchPassword='" + matchPassword + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", nrcNumber='" + nrcNumber + '\'' +
                ", enabled=" + enabled +
                ", booking=" + booking +
                '}';
    }
}

