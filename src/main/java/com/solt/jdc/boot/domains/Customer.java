package com.solt.jdc.boot.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String password;
    @NotNull
    private String phone;
    private String email;
    private String nrcNumber;
    private boolean isDeactivated;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    private Booking booking;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNrcNumber() {
        return nrcNumber;
    }

    public void setNrcNumber(String nrcNumber) {
        this.nrcNumber = nrcNumber;
    }

    public boolean isDeactivated() {
        return isDeactivated;
    }

    public void setDeactivated(boolean deactivated) {
        isDeactivated = deactivated;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer Customer = (Customer) o;

        if (id != Customer.id) return false;
        if (isDeactivated != Customer.isDeactivated) return false;
        if (username != null ? !username.equals(Customer.username) : Customer.username != null) return false;
        if (firstName != null ? !firstName.equals(Customer.firstName) : Customer.firstName != null) return false;
        if (lastName != null ? !lastName.equals(Customer.lastName) : Customer.lastName != null) return false;
        if (password != null ? !password.equals(Customer.password) : Customer.password != null) return false;
        if (phone != null ? !phone.equals(Customer.phone) : Customer.phone != null) return false;
        if (email != null ? !email.equals(Customer.email) : Customer.email != null) return false;
        return nrcNumber != null ? nrcNumber.equals(Customer.nrcNumber) : Customer.nrcNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (nrcNumber != null ? nrcNumber.hashCode() : 0);
        result = 31 * result + (isDeactivated ? 1 : 0);
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
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", nrcNumber='" + nrcNumber + '\'' +
                ", isDeactivated=" + isDeactivated +
                '}';
    }
}

