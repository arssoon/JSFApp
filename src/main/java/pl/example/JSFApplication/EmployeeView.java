package pl.example.JSFApplication;

import pl.example.JSFApplication.entity.Employee;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("employeeView")
@ViewScoped
public class EmployeeView {
    private List<Employee> employees;
    private EmployeeService service;

    public EmployeeView() {
        employees = new ArrayList<>();
        service = new EmployeeService();
//        employeeList.add(new Employee(1, "Aro", "baza", 123, "test", "test"));

        employees = service.getEmployeeList();
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
