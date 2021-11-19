package pl.example.JSFApplication;

import pl.example.JSFApplication.entity.Employee;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("employeeView")
@ApplicationScoped
public class EmployeeView {
    private static List<Employee> employees;

    @PostConstruct
    public void init() {
        employees = new ArrayList<>();

        employees.add(new Employee(1, " asdf", "hahha", 12, "asfdas", "asdf"));
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
