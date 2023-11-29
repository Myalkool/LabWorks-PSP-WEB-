/*
package by.iba.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConnectorDB {
    private static final Logger logger = LogManager.getLogger(ConnectorDB.class);
    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("db",
                Locale.getDefault());
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");

        DriverManager.registerDriver(new org.postgresql.Driver());
        logger.info("connection establish");
        return DriverManager.getConnection(url, user, pass);
    }

}

//Connection cn = ConnectorDB.getConnection();*/
