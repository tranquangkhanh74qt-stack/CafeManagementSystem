package org.example.demo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String SERVER = "localhost";
    private static final String DATABASE = "CafeDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456"; // Thay đổi theo mật khẩu của bạn
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://" + SERVER + ":1433;databaseName=" + DATABASE;
        return DriverManager.getConnection(connectionUrl, USER, PASSWORD);
    }
    
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}