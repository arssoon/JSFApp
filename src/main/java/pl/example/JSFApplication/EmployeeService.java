package pl.example.JSFApplication;


import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.example.JSFApplication.dao.EmployeeDao;
import pl.example.JSFApplication.entity.Employee;
import pl.example.JSFApplication.factory.HibernateFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployeeService  {
    private List<Employee> employeeList;
    public EmployeeDao employeeDao;

    public EmployeeService() {
        employeeList = new ArrayList<>();
        employeeDao = new EmployeeDao();
//        employeeList.add(new Employee(1, "Aro", "baza", 123, "test", "test"));

        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            employeeList = employeeDao.findAllEmployees(session);
            session.getTransaction().commit();
        } catch (HibernateError ex) {
            ex.printStackTrace();
        }
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getEmployeeList() {
        return new ArrayList(employeeList);
    }

    public List<Employee> getEmployee(int size) {

        if (size > employeeList.size()) {
            Random rand = new Random();

            List<Employee> randomList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int randomIndex = rand.nextInt(employeeList.size());
                randomList.add(employeeList.get(randomIndex));
            }

            return randomList;
        } else {
            return new ArrayList(employeeList.subList(0, size));
        }

    }

}
