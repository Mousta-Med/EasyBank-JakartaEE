package com.easybankjakarta.Services;

import com.easybankjakarta.Dao.EmployeDao;
import com.easybankjakarta.Models.Employe;

import java.util.List;
import java.util.Optional;

public class EmployeService {
    private final EmployeDao employeDao;

    public EmployeService(EmployeDao employeDao) {
        this.employeDao = employeDao;
    }

    public Optional<Employe> addEmploye(Employe employe) {
        Optional<Employe> optionalEmploye = employeDao.addEmploye(employe);
        if (optionalEmploye.isPresent()) {
            return optionalEmploye;
        } else {
            return Optional.empty();
        }
    }

    public Integer deleteEmploye(String matricule) {
        Integer res = employeDao.deleteEmploye(matricule);
        if (res != 0) {
            return res;
        } else {
            return res;
        }
    }

    public Optional<Employe> updateEmploye(Employe Employe) {
        Optional<Employe> optionalEmploye = employeDao.updateEmploye(Employe);
        if (optionalEmploye.isPresent()) {
            return optionalEmploye;
        } else {
            return Optional.empty();
        }
    }

    public Optional<Employe> getEmploye(String matricule) {
        Optional<Employe> optionalEmploye = employeDao.getEmploye(matricule);
        if (optionalEmploye.isPresent()) {
            return optionalEmploye;
        } else {
            return Optional.empty();
        }
    }

    public List<Employe> getEmployees() {
        List<Employe> Employees = employeDao.getEmployees();
        return Employees;
    }
}
