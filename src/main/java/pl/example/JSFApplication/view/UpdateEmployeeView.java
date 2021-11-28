package pl.example.JSFApplication.view;

import lombok.Getter;
import lombok.Setter;
import pl.example.JSFApplication.dao.EmployeeDao;
import pl.example.JSFApplication.entity.Employee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ManagedBean(name = "updateEmployeeView")
@ViewScoped
public class UpdateEmployeeView {
    private List<Employee> employees;
    private EmployeeDao service;
    private int id;
    private String name;
    private String surname;
    private int age;
    private String nrPhone;
    private String email;
    private static Employee employee;

    public UpdateEmployeeView() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        employees = new ArrayList<>();
        service = new EmployeeDao();
        employee = new Employee();
    }

    public List<Employee> getEmployeesById() {
        employees = service.findEmployeesById(id);
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
}
