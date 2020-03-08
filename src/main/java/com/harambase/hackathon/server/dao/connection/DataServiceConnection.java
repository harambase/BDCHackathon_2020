package com.harambase.hackathon.server.dao.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataServiceConnection {

    private final static Logger logger = LoggerFactory.getLogger(DataServiceConnection.class);

    public static Connection openDBConnection() {
        try {
            // Load driver and link to driver manager
            Class.forName(DataServiceProperty.DRIVER);
            // Create a connection to the specified connection
            return DriverManager.getConnection(DataServiceProperty.URL, DataServiceProperty.USERNAME, DataServiceProperty.PASSWORD);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static Connection openDBConnectionWithUandP(String username, String password) {
        try {
            // Load driver and link to driver manager
            Class.forName(DataServiceProperty.DRIVER);
            // Create a connection to the specified connection
            return DriverManager.getConnection(DataServiceProperty.URL, username, password);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
