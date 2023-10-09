package com.easybankjakarta.Dao.impl;

import com.easybankjakarta.Dao.ClientDao;
import com.easybankjakarta.Models.Client;
import com.easybankjakarta.Util.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDaoImpl implements ClientDao {
    Connection connection = DbConnection.createConnection();
    Client client = new Client();

    @Override
    public Optional<Client> addClient(Client client) {
        String query = "INSERT INTO personne(nom, prenome, dateNaissance, telephone) VALUES (?,?,?,?) RETURNING id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setDate(3, Date.valueOf(client.getBirthDate()));
            preparedStatement.setString(4, client.getMobile());
            ResultSet result = preparedStatement.executeQuery();
            Integer personneId = 0;
            if (result.next()) {
                personneId = result.getInt(1);
            }
            String stmt = "INSERT INTO client VALUES (?,?,?)";
            PreparedStatement statment = connection.prepareStatement(stmt);
            statment.setString(1, client.getCode());
            statment.setString(2, client.getAddress());
            statment.setInt(3, personneId);
            Integer integer = statment.executeUpdate();
            if (integer != 0) {
                return Optional.of(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Integer deleteClient(String code) {
        String query = "DELETE FROM personne WHERE id = (SELECT personne FROM client WHERE code = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
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
    public Optional<Client> updateClient(Client client) {
        String query = "UPDATE client SET adresse = ? WHERE code = ? RETURNING personne";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getAddress());
            preparedStatement.setString(2, client.getCode());
            ResultSet result = preparedStatement.executeQuery();
            Integer personneId = 0;
            if (result.next()) {
                personneId = result.getInt(1);
            }
            String stmt = "UPDATE personne SET nom = ?, prenome = ?, dateNaissance = ?, telephone = ? WHERE id = ?";
            PreparedStatement statment = connection.prepareStatement(stmt);
            statment.setString(1, client.getFirstName());
            statment.setString(2, client.getLastName());
            statment.setDate(3, Date.valueOf(client.getBirthDate()));
            statment.setString(4, client.getMobile());
            statment.setInt(5, personneId);
            Integer integer = statment.executeUpdate();
            if (integer != 0) {
                return Optional.of(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> getClient(String code) {
        String query = "SELECT * FROM client WHERE code = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet result = preparedStatement.executeQuery();
            Integer personneId = 0;
            if (result.next()) {
                personneId = result.getInt("personne");
            }
            String stmt = "SELECT * FROM personne WHERE id = ?";
            PreparedStatement statment = connection.prepareStatement(stmt);
            statment.setInt(1, personneId);
            ResultSet resultSet = statment.executeQuery();
            if (resultSet.next()) {
                client.setCode(result.getString("code"));
                client.setFirstName(resultSet.getString("nom"));
                client.setLastName(resultSet.getString("prenome"));
                client.setBirthDate(resultSet.getDate("datenaissance").toLocalDate());
                client.setMobile(resultSet.getString("telephone"));
                client.setAddress(result.getString("adresse"));
                return Optional.of(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> getClientByAttribut(String string) {
        String query = "SELECT * FROM personne WHERE nom LIKE ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, string);
            ResultSet result = preparedStatement.executeQuery();
            Integer personneId = 0;
            if (result.next()) {
                personneId = result.getInt("id");
            }
            String stmt = "SELECT * FROM client WHERE personne = ?";
            PreparedStatement statment = connection.prepareStatement(stmt);
            statment.setInt(1, personneId);
            ResultSet resultSet = statment.executeQuery();
            if (resultSet.next()) {
                client.setCode(resultSet.getString("code"));
                client.setFirstName(result.getString("nom"));
                client.setLastName(result.getString("prenome"));
                client.setBirthDate(result.getDate("datenaissance").toLocalDate());
                client.setMobile(result.getString("telephone"));
                client.setAddress(resultSet.getString("adresse"));
                return Optional.of(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Client> getClients() {
        String query = "SELECT * FROM personne INNER JOIN client ON personne.id = client.personne";
        List<Client> clients = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Client addClient = new Client();
                addClient.setCode(result.getString("code"));
                addClient.setFirstName(result.getString("nom"));
                addClient.setLastName(result.getString("prenome"));
                addClient.setBirthDate(result.getDate("datenaissance").toLocalDate());
                addClient.setMobile(result.getString("telephone"));
                addClient.setAddress(result.getString("adresse"));
                clients.add(addClient);
            }
            return clients;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
