package com.easybankjakarta.Dao.impl;

import com.easybankjakarta.Dao.EmployeDao;
import com.easybankjakarta.Models.Employe;
import com.easybankjakarta.Util.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeDaoImpl implements EmployeDao {
    Connection connection = DbConnection.createConnection();
    Employe employe = new Employe();

    @Override
    public Optional<Employe> addEmploye(Employe employe) {
        String query = "INSERT INTO personne(nom, prenome, dateNaissance, telephone) VALUES (?,?,?,?) RETURNING id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employe.getFirstName());
            preparedStatement.setString(2, employe.getLastName());
            preparedStatement.setDate(3, Date.valueOf(employe.getBirthDate()));
            preparedStatement.setString(4, employe.getMobile());
            ResultSet result = preparedStatement.executeQuery();
            int personneId = 0;
            if (result.next()) {
                personneId = result.getInt(1);
            }
            String stmt = "INSERT INTO employe VALUES (?,?,?,?)";
            PreparedStatement statment = connection.prepareStatement(stmt);
            statment.setString(1, employe.getMatricule());
            statment.setDate(2, Date.valueOf(employe.getRecruitmentDate()));
            statment.setString(3, employe.getEmail());
            statment.setInt(4, personneId);
            Integer integer = statment.executeUpdate();
            if (integer != 0) {
                return Optional.of(employe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Integer deleteEmploye(String matricul) {
        String query = "DELETE FROM personne WHERE id = (SELECT personneId FROM employe WHERE matricule = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, matricul);
            Integer result = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (result != 0) {
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Employe> getEmploye(String matricule) {
        String query = "SELECT * FROM employe WHERE matricule = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, matricule);
            ResultSet result = preparedStatement.executeQuery();
            Integer personneId = 0;
            if (result.next()) {
                personneId = result.getInt("personneId");
            }
            String stmt = "SELECT * FROM personne WHERE id = ?";
            PreparedStatement statment = connection.prepareStatement(stmt);
            statment.setInt(1, personneId);
            ResultSet resultSet = statment.executeQuery();
            if (resultSet.next()) {
                employe.setMatricule(result.getString("matricule"));
                employe.setFirstName(resultSet.getString("nom"));
                employe.setLastName(resultSet.getString("prenome"));
                employe.setBirthDate(resultSet.getDate("datenaissance").toLocalDate());
                employe.setMobile(resultSet.getString("telephone"));
                employe.setEmail(result.getString("email"));
                employe.setRecruitmentDate(result.getDate("daterecrutement").toLocalDate());
                return Optional.of(employe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employe> getEmployeByAttribut(String string) {
        String query = "SELECT * FROM personne WHERE nom LIKE ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, string);
            ResultSet result = preparedStatement.executeQuery();
            Integer personneId = 0;
            if (result.next()) {
                personneId = result.getInt("id");
            }
            String stmt = "SELECT * FROM employe WHERE personneId = ?";
            PreparedStatement statment = connection.prepareStatement(stmt);
            statment.setInt(1, personneId);
            ResultSet resultSet = statment.executeQuery();
            if (resultSet.next()) {
                employe.setMatricule(resultSet.getString("matricule"));
                employe.setFirstName(result.getString("nom"));
                employe.setLastName(result.getString("prenome"));
                employe.setBirthDate(result.getDate("datenaissance").toLocalDate());
                employe.setMobile(result.getString("telephone"));
                employe.setEmail(resultSet.getString("email"));
                employe.setRecruitmentDate(resultSet.getDate("daterecrutement").toLocalDate());
                return Optional.of(employe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employe> updateEmploye(Employe employe) {
        String updateEmployeQuery = "UPDATE employe SET dateRecrutement = ?, email = ? WHERE matricule = ? RETURNING personneId";
        try {
            PreparedStatement employeStatement = connection.prepareStatement(updateEmployeQuery);
            employeStatement.setDate(1, Date.valueOf(employe.getRecruitmentDate()));
            employeStatement.setString(2, employe.getEmail());
            employeStatement.setString(3, employe.getMatricule());
            ResultSet resultSet = employeStatement.executeQuery();
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt("personneId");
            }
            String updatePersonneQuery = "UPDATE personne SET nom = ?, prenome = ?, dateNaissance = ?, telephone = ? WHERE id = ?";
            PreparedStatement personneStatement = connection.prepareStatement(updatePersonneQuery);
            personneStatement.setString(1, employe.getFirstName());
            personneStatement.setString(2, employe.getLastName());
            personneStatement.setDate(3, Date.valueOf(employe.getBirthDate()));
            personneStatement.setString(4, employe.getMobile());
            personneStatement.setInt(5, id);
            Integer res = personneStatement.executeUpdate();
            if (res != 0) {
                return Optional.of(employe);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Employe> getEmployes() {
        String query = "SELECT * FROM personne INNER JOIN employe ON personne.id = employe.personneId";
        List<Employe> employes = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Employe addEmploye = new Employe();
                addEmploye.setMatricule(result.getString("matricule"));
                addEmploye.setFirstName(result.getString("nom"));
                addEmploye.setLastName(result.getString("prenome"));
                addEmploye.setBirthDate(result.getDate("datenaissance").toLocalDate());
                addEmploye.setMobile(result.getString("telephone"));
                addEmploye.setEmail(result.getString("email"));
                addEmploye.setRecruitmentDate(result.getDate("daterecrutement").toLocalDate());
                employes.add(addEmploye);
            }
            return employes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
