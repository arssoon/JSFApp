package pl.example.JSFApplication.factory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import pl.example.JSFApplication.entity.Employee;

public class HibernateFactory {
//
//    private static Configuration getHibernateConfig() {
//        Configuration configuration = new Configuration();
////        configuration.addResource("Employee.hbm.xml");
//        configuration.setProperty("hibernate.connection.url", "jdbc:sqlserver://ARSON\\MSSQLEXPRESS:54233; " +
//                "databaseName=work");
//        configuration.setProperty("hibernate.connection.username", "arson");
//        configuration.setProperty("hibernate.connection.password", "marker25");
//        configuration.setProperty("hibernate.dialect", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        configuration.setProperty("connection.driver_class", "com.mysql.jdbc.Driver");
//        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
//        configuration.setProperty("hibernate.connection.autocommit", "true");
//        configuration.addAnnotatedClass(Employee.class);
//        return configuration;
//    }
//
//    public static SessionFactory getSessionFactory() {
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
//                applySettings(getHibernateConfig().getProperties()).build();
//
//        return getHibernateConfig().buildSessionFactory(registry);
//    }

    public static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}