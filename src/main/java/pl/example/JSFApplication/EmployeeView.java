package pl.example.JSFApplication;

import pl.example.JSFApplication.entity.Employee;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("employeeView")
@ViewScoped
public class EmployeeView {
    private List<Employee> employees;

    @Inject
    private EmployeeService service;

    @PostConstruct
    public void init() {
        employees = new ArrayList<>();
        employees = service.getEmployeeList();
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdStudentId",  studentObj.getId());

    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
