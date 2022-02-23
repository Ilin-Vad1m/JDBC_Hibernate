package jm.task.core.jdbc.util;

import com.fasterxml.classmate.AnnotationConfiguration;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.Properties;

public class Util {



//    static {
//            Properties properties = new Properties();
//
//            properties.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
//            properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydatabase?serverTimezone=Europe/Moscow");
//            properties.setProperty("hibernate.connection.username", "root");
//            properties.setProperty("hibernate.connection.password", "786512");
//            properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
//            properties.setProperty("hibernate.hbm2ddl.auto", "update");
//            properties.setProperty("show_sql", "true");
//            properties.setProperty("hibernate.connection.autoReconnect", "true");
//
//            Configuration configuration = new Configuration().addProperties(properties).addAnnotatedClass(User.class);
//
//    }

    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase?serverTimezone=Europe/Moscow", "root", "786512");
    }


    private static Properties addProperties(){
        Properties properties = new Properties();

        properties.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydatabase?serverTimezone=Europe/Moscow");
        properties.setProperty("hibernate.connection.username", "root");
        properties.setProperty("hibernate.connection.password", "786512");
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("show_sql", "true");
        properties.setProperty("hibernate.connection.autoReconnect", "true");

        return  properties;
    }

    public static Session getSession()throws HibernateException {
        SessionFactory concreteSessionFactory = new Configuration().addProperties(addProperties()).addAnnotatedClass(User.class).buildSessionFactory();
        return concreteSessionFactory.openSession();
    }
}