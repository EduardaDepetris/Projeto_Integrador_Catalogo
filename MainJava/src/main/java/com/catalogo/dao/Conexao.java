package com.catalogo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/catalogo";
    private static final String USER = "root";
    private static final String PASSWORD = "Integrac@o1";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do MySQL não encontrado!", e);
        }
    }
}