package pl.example.JSFApplication;

import pl.example.JSFApplication.entity.Employee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "employeeView", eager = true)
@RequestScoped
public class EmployeeView {
    private List<Employee> employees;
    private EmployeeService service;
    private int id;
    private String name;
    private String surname;
    private int age;
    private String nrPhone;
    private String email;
    private static Employee employee;

    public EmployeeView() {
        employees = new ArrayList<>();
        service = new EmployeeService();
        employee = new Employee();

        employees = service.getEmployeeList();
    }

    public String saveEmployee(){
        Integer userId= service.getId();
        Employee employee = new Employee(userId, name, surname, age, nrPhone, email);
        service.save(employee);

        return "output";
    }
    public List<Employee> getStudentDetailsById() {
        employees = service.findEmployeeById(id);

        for(Employee employee : employees) {
            id = employee.getId();
            name = employee.getName();
            surname = employee.getSurname();
            age = employee.getAge();
            nrPhone = employee.getNrPhone();
            email = employee.getEmail();
        }
        System.out.println("Fetched Id? " + id + " Details Are: Name=" + name + ", Surname=" + surname + ", Age=" + age
                + ", Nr Phone=" + nrPhone + ", Email=" + email);
        return employees;
    }

    public void updateEmployee() {
        service.updateEmployee(employee);
    }

    public void deleteEmployee() {
        service.deleteEmpoyee(id);
    }


    public static Employee getEmployee() {
        return employee;
    }

    public static void setEmployee(Employee employee) {
        EmployeeView.employee = employee;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNrPhone() {
        return nrPhone;
    }

    public void setNrPhone(String nrPhone) {
        this.nrPhone = nrPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public EmployeeService getService() {
        return service;
    }
    public void setService(EmployeeService service) {
        this.service = service;
    }
}
