package com.jasdhir.dev.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;





public class ConnectionUtil {

	static Logger logger = LogManager.getLogger(ConnectionUtil.class.getName());

    public static Connection createConnection() {

        try {
            //jdbc:postgresql://35.226.135.224:5432/ToDoDB?user=user&password=password
            Connection conn = DriverManager.getConnection(System.getenv("DB_URL"));
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
          logger.fatal("Connection could not be established",e);
            return null;
        }

    }
}

