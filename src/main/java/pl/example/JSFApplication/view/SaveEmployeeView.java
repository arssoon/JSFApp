package pl.example.JSFApplication.view;

import lombok.Getter;
import lombok.Setter;
import pl.example.JSFApplication.dao.EmployeeDao;
import pl.example.JSFApplication.entity.Employee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@Getter
@Setter
@ManagedBean(name = "saveEmployeeView")
@ViewScoped
public class SaveEmployeeView {
    private EmployeeDao service;
    private int id;
    private String name;
    private String surname;
    private int age;
    private String nrPhone;
    private String email;

    public SaveEmployeeView() {
        service = new EmployeeDao();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
    }

    public void saveEmployee() {
        Integer userId = service.getId();
        Employee employee = new Employee(userId, name, surname, age, nrPhone, email);
        service.save(employee);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdEmployeeId", employee.getId());
    }
}
