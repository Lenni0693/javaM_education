package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private String url;
    private String name;
    private String password;
    public Util() {
        url = "jdbc:mysql://localhost:3306/java_mentor?serverTimezone=Europe/Moscow";
        name = "Lenie";
        password = "123";
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
    public SessionFactory getSessionFactory() {
        SessionFactory mySessionFactory = null;
        try {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", url);
            prop.setProperty("hibernate.connection.username", name);
            prop.setProperty("hibernate.connection.password", password);
            prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            prop.setProperty("hibernate.hbm2ddl.auto", "create");

            mySessionFactory = new org.hibernate.cfg.Configuration()
                    .addProperties(prop)
                    //.addPackage("com.kat")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory()
            ;

        } catch(HibernateException e) {
            System.out.println(e.getMessage());
        }
        return mySessionFactory;
    }
}
