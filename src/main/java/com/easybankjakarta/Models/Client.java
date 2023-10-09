package com.easybankjakarta.Models;

import java.time.LocalDate;

public final class Client extends Person {
    private String code;
    private String address;

    public Client(String firstName, String lastName, LocalDate birthDate, String mobiel, String code, String address) {
        super(firstName, lastName, birthDate, mobiel);
        this.code = code;
        this.address = address;
    }

    public Client() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
