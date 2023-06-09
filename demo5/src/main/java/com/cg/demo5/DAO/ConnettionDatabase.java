package com.cg.demo5.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnettionDatabase {
    private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/md3product";
    private String jdbcUsername = "root";
    //                            password của mình
    private String jdbcPassword = "nghia1992";
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
