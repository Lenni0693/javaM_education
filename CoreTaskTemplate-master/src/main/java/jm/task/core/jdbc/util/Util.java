package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private String url;
    private String name;
    private String password;
    public Util() {
        url = "jdbc:mysql://localhost:3306/java_mentor?serverTimezone=Europe/Moscow";
        name = "Lenie";
        password = "LenieMail";
    }
    public Connection getConnection() {
        Connection con = null;

        try{

            con = DriverManager.getConnection(url,name,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
