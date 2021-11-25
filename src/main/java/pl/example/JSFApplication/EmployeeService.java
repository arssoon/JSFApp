package pl.example.JSFApplication;


import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import pl.example.JSFApplication.dao.EmployeeDao;
import pl.example.JSFApplication.entity.Employee;

import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployeeService {
    private List<Employee> employeeList;
    public EmployeeDao employeeDao;

    private static Transaction transObj;
    private static Session session = new Configuration().configure().buildSessionFactory().openSession();

    public EmployeeService() {
        employeeList = new ArrayList<>();
        employeeDao = new EmployeeDao();

        try {
            transObj = session.beginTransaction();
            employeeList = employeeDao.findAllEmployees(session);
        } catch (HibernateError ex) {
            ex.printStackTrace();
        } finally {
            transObj.commit();
        }
    }
    public void save(Employee employee) {
        try {
            transObj = session.beginTransaction();
            employeeDao.add(employee, session);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdEmployeeId", employee.getId());
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        } finally {
            transObj.commit();
        }
    }

    public void updateEmployee(Employee employee) {
        try {
            transObj = session.beginTransaction();
            employeeDao.update(employee.getId(), employee, session);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("updatedStudentRecord", "Success");
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        }
        finally {
            transObj.commit();
        }
    }

    public void deleteEmpoyee(int id) {
        try {
            transObj = session.beginTransaction();
            employeeDao.delete(id, session);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deletedStudentId",  id);

        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        }
        finally {
            transObj.commit();
        }
    }

    public List <Employee> findEmployeeById(int id) {
        List <Employee> employees = new ArrayList<>();
        try {
            transObj = session.beginTransaction();
            employees = employeeDao.findById(id, session);
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        }
        finally {
            transObj.commit();
        }

        return employees;
    }

    public Integer getId() {
        String hql = "select max(employee.id) from Employee employee";
        Query query = session.createQuery(hql);
        List<Integer> results = query.list();
        Integer employeeId = 10;
        if (results.get(0) != null) {
            employeeId = results.get(0) + 1;
        }
        return employeeId;
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
