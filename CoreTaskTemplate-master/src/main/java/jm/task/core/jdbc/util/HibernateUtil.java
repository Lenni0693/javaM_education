package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Properties;

public class HibernateUtil {
    private  Session mySession;
    private String url;
    private String name;
    private String password;
    public HibernateUtil(){
        url = "jdbc:mysql://localhost:3306/java_mentor?serverTimezone=Europe/Moscow";
        name = "Lenie";
        password = "LenieMail";
    }
    public Session getConnection() {
        try {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", url);
            prop.setProperty("hibernate.connection.username", name);
            prop.setProperty("hibernate.connection.password", password);
            prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            prop.setProperty("hibernate.hbm2ddl.auto", "create");

            SessionFactory mySessionFactory = new org.hibernate.cfg.Configuration()
                    .addProperties(prop)
                    //.addPackage("com.kat")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory()
            ;
            mySession = mySessionFactory.openSession();
        } catch(HibernateException e) {
            System.out.println(e.getMessage());
        }
        return mySession;
    }
}
