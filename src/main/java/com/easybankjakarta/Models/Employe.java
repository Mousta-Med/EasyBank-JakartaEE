package com.easybankjakarta.Models;

import java.time.LocalDate;

public final class Employe extends Person{
    private String matricule;
    private LocalDate recruitmentDate;
    private String email;

    public Employe(String firstName, String lastName, LocalDate birthdate, String mobiel, String matricule, LocalDate recruitmentDate, String email) {
        super(firstName, lastName, birthdate, mobiel);
        this.matricule = matricule;
        this.recruitmentDate = recruitmentDate;
        this.email = email;
    }

    public Employe() {
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public LocalDate getRecruitmentDate() {
        return recruitmentDate;
    }

    public void setRecruitmentDate(LocalDate recruitmentDate) {
        this.recruitmentDate = recruitmentDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
