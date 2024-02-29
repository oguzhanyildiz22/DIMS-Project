package org.example.dimsproject.repository;

import org.example.dimsproject.model.Adviser;

import java.sql.*;
import java.util.Optional;

public class AdviserRepository {
    //JDBC
    private final String URL = "jdbc:mysql://localhost:3306/dimsproject";
    private final String USER = "root";
    private final String PASSWORD = "12345";
    private Connection connection =null;
    private Statement statement =null;
    private ResultSet resultSet =null;

    //FETCH - SAVE - UPDATE
    // Fetch data from database.
    public Optional<Adviser> getAdvisorById(int id){
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            statement = connection.createStatement();
            String query = "SELECT * FROM advisers WHERE id="+id;
            resultSet = statement.executeQuery(query);
            Adviser adviser = null;
            while(resultSet.next()){
                adviser = new Adviser();
                adviser.setId(resultSet.getInt(1));
                adviser.setName(resultSet.getString(2));
                adviser.setDepartment(resultSet.getString(3));
            }
            connection.close();
            return Optional.ofNullable(adviser);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Save data to database
    public void save(Adviser adviser){
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            String query = "INSERT INTO advisers VALUES (" + adviser.getId() +",'" + adviser.getName() + "','" + adviser.getDepartment() +"');";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Update data of database
    public void update(Adviser adviser){
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            statement = connection.createStatement();
            String query = "UPDATE advisers SET name='"+adviser.getName()+"',department='"+adviser.getDepartment()+"' WHERE id="+adviser.getId()+";";
            statement.executeUpdate(query);
            //connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Delete data from database
    public void deleteById(int id){
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            statement = connection.createStatement();
            String query = "DELETE FROM advisers WHERE id="+id+";";
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
