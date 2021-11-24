package pl.example.JSFApplication;

import org.hibernate.*;
import pl.example.JSFApplication.dao.EmployeeDao;
import pl.example.JSFApplication.entity.Employee;
import pl.example.JSFApplication.factory.HibernateFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class EmployeeService implements Serializable {

    private List<Employee> employeeList;

//    private static Transaction transObj;
//    private static Session session;

    @Inject
    private EmployeeDao employeeDao;

    @PostConstruct
    public void init() {
        employeeList = new ArrayList<>();
//        employeeList.add(new Employee(1, "Aro", "baza", 123, "test", "test"));

        try {
            SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            employeeList = employeeDao.findAllEmployees(session);
            session.getTransaction().commit();
        } catch (HibernateError ex) {
            ex.printStackTrace();
        }
    }

//    public List<Employee> retrieveStudent() {
//        Employee studentsObj;
//        List<Employee>allStudents = new ArrayList();
//        try {
//            transObj = sessionObj.beginTransaction();
//            Query queryObj = sessionObj.createQuery("from Employee");
//            allStudents = queryObj.list();
//            for(Employee stud : allStudents) {
//                studentsObj = new Employee();
//                studentsObj.setName(stud.getName());
//                allStudents.add(studentsObj);
//            }
//            System.out.println("All The Students Records Are Fetched Successfully From Database");
//
//            // XHTML Response Text
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findStudentById", "true");
//        } catch(Exception exceptionObj) {
//            exceptionObj.printStackTrace();
//        } finally {
//            transObj.commit();
//        }
//        return allStudents;
//    }

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
