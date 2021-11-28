package pl.example.JSFApplication.view;

import com.sun.xml.bind.v2.schemagen.xmlschema.AttributeType;
import lombok.Getter;
import lombok.Setter;
import pl.example.JSFApplication.dao.EmployeeDao;
import pl.example.JSFApplication.entity.Employee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ManagedBean(name = "employeeView")
@ViewScoped
public class EmployeeView {
    private List<Employee> employees;
    private Map<Boolean, Integer> checked;

    private EmployeeDao service;
    private int id;
    private String name;
    private String surname;
    private int age;
    private String nrPhone;
    private String email;
    private boolean checkDelete;
    private boolean checkUpdate;

    public EmployeeView() {
        service = new EmployeeDao();
        checked = new HashMap();
        checkDelete = false;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();

        employees = service.getListOfEmployees();
    }

    public void deleteEmployee(int id) {
        service.deleteEmpoyee(id);

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deletedEmployee", id);
    }

}
