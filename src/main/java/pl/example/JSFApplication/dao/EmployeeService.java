package pl.example.JSFApplication.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import pl.example.JSFApplication.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Getter
@Setter
@ApplicationScoped
public class EmployeeService {

    public List <Employee> getListOfEmployees() {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        try {
            session.beginTransaction();
            List <Employee> employees = session.createQuery("from Employee", Employee.class).getResultList();
            session.getTransaction().commit();
            return employees;
        } catch (HibernateError ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public Employee save(Employee employee) {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        try {
            session.beginTransaction();

            session.save(employee);
            session.getTransaction().commit();

            return employee;
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public Employee updateEmployee(Employee employeeNew) {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        try {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeNew.getId());
            employee.setName(employeeNew.getName());
            employee.setSurname(employeeNew.getSurname());
            employee.setAge(employeeNew.getAge());
            employee.setNrPhone(employeeNew.getNrPhone());
            employee.setEmail(employeeNew.getEmail());

            session.update(employee);
            session.getTransaction().commit();

            return employee;
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public Employee deleteEmpoyee(int id) {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        try {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            session.delete(employee);
            session.getTransaction().commit();
            return employee;
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Employee> findEmployeeById(int id) {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<Employee> employees = session.createQuery("from Employee where id=" + id, Employee.class).getResultList();
            session.getTransaction().commit();
            return employees;
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public Integer getId() {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        String hql = "select max(employee.id) from Employee employee";
        List<Integer> results = session.createQuery(hql).getResultList();

        if (results.get(0) != null) {
            return results.get(0) + 1;
        }
        return 0;
    }
}
