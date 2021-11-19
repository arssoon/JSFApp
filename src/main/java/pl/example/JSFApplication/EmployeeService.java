package pl.example.JSFApplication;

import pl.example.JSFApplication.entity.Employee;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Named
@ApplicationScoped
public class EmployeeService {

    private List<Employee> employeeList;
    private String s1;

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    @PostConstruct
    public void init() {
        employeeList = new ArrayList<>();

        employeeList.add(new Employee(1, " Arek", "Test", 12, "123", "as@wp.pl"));
        employeeList.add(new Employee(2, " Arek", "Test", 12, "123", "as@wp.pl"));
        employeeList.add(new Employee(3, " Arek", "Test", 12, "123", "as@wp.pl"));
        employeeList.add(new Employee(4, " Arek", "Test", 12, "123", "as@wp.pl"));
        employeeList.add(new Employee(5, " Arek", "Test", 12, "123", "as@wp.pl"));
        s1 = "To jest text";
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
        }

        else {
            return new ArrayList(employeeList.subList(0, size));
        }

    }

}
