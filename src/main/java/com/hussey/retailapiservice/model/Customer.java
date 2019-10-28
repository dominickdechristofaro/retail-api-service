package com.hussey.retailapiservice.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {

    private Integer customerId;
    @NotEmpty(message = "Name value should not be empty")
    @Size(max = 50, message = "Name was too long")
    private String firstName;
    @NotEmpty(message = "Name value should not be empty")
    @Size(max = 50, message = "Name was too long")
    private String lastName;
    @NotEmpty(message = "Street value should not be empty")
    @Size(max = 50, message = "Street name was too long")
    private String street;
    @NotEmpty(message = "City value should not be empty")
    @Size(max = 50, message = "City name was too long")
    private String city;
    @NotEmpty(message = "Zipcode should not be empty")
    private String zipcode;
    @Email(message = "Must be a valid email address")
    @Size(max = 75, message = "email was too long")
    private String email;
    @Size(max = 20, message = "Phone number was too long")
    @NotEmpty(message = "Phone number must not be empty")
    private String phone;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String street, String city, String zipcode, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.email = email;
        this.phone = phone;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) &&
                firstName.equals(customer.firstName) &&
                lastName.equals(customer.lastName) &&
                street.equals(customer.street) &&
                city.equals(customer.city) &&
                zipcode.equals(customer.zipcode) &&
                email.equals(customer.email) &&
                phone.equals(customer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, street, city, zipcode, email, phone);
    }
}
