package com.company.data;

import com.company.data.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQL implements IDB {
    private static PostgreSQL instance;
    private Connection connection;



    private PostgreSQL(){
        try {
            Class.forName("org.postgresql.Driver");
            String password = "";
            String username = "";
            String url = "jdbc:postgresql://localhost:5432/------";
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException var2) {
            System.out.println("Database Connection Creation Failed : " + var2.getMessage());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public Connection getConnection() {
        return this.connection;
    }

    public static PostgreSQL getInstance() throws SQLException {
        if (instance == null) {
            instance = new PostgreSQL();
        } else if (instance.getConnection().isClosed()) {
            instance = new PostgreSQL();
        }

        return instance;
    }
}
