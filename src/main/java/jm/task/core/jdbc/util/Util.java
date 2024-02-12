package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";
    private static Connection connection = null;

    public static Statement getStatement() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection.createStatement();
    }

    public static void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Ошибка закрытия соединения: " + e);
        }
    }

}
