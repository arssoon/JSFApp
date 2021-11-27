package pl.example.JSFApplication;

import lombok.Getter;
import lombok.Setter;
import pl.example.JSFApplication.dao.EmployeeService;
import pl.example.JSFApplication.entity.Employee;

import javax.faces.bean.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ManagedBean(name = "employeeView")
@ViewScoped
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
    private boolean deleteEmployee;

    public EmployeeView() {
        employees = new ArrayList<>();
        service = new EmployeeService();
        employee = new Employee();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();

        employees = service.getListOfEmployees();
    }

    public void saveEmployee(){
        Integer userId= service.getId();
        Employee employee = new Employee(userId, name, surname, age, nrPhone, email);
        service.save(employee);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdEmployeeId", employee.getId());
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
        return employees;
    }

    public void updateEmployee() {
        employee.setId(id);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setAge(age);
        employee.setNrPhone(nrPhone);
        employee.setEmail(email);

        service.updateEmployee(employee);

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("updatedEmployee", "Success");
    }

    public void deleteEmployee() {
        service.deleteEmpoyee(id);

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deletedEmployee", id);
    }

}
