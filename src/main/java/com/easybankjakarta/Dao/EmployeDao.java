package com.easybankjakarta.Dao;

import com.easybankjakarta.Models.Employe;

import java.util.List;
import java.util.Optional;

public interface EmployeDao {
    Optional<Employe> addEmploye(Employe employe);
    Integer deleteEmploye(String matricule);
    Optional<Employe> getEmploye(String matricule);
    Optional<Employe> getEmployeByAttribut(String string);
    Optional<Employe> updateEmploye(Employe e);
    List<Employe> getEmployees();
}
