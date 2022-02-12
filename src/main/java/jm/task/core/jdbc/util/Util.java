package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase?serverTimezone=Europe/Moscow", "root", "786512");
    }

}