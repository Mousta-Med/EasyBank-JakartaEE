package com.easybankjakarta.Models;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;
    protected String mobile;

    public Person(String firstName, String lastName, LocalDate birthDate, String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.mobile = mobile;
    }

    public Person() {
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(birthDate, person.birthDate) && Objects.equals(mobile, person.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDate, mobile);
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + firstName + '\'' +
                ", prenom='" + lastName + '\'' +
                ", dateNaissance=" + birthDate +
                ", telephone='" + mobile + '\'' +
                '}';
    }
}
