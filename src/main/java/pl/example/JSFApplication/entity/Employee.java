package pl.example.JSFApplication.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "pracownicy")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prac_id")
    private int id;

    @Column(name = "prac_imie")
    private String name;

    @Column(name = "prac_nazwisko")
    private String surname;

    @Column(name = "prac_wiek")
    private int age;

    @Column(name = "prac_nr_telefonu")
    private String nrPhone;

    @Column(name = "prac_email")
    private String email;

    public Employee(String name, String surname, int age, String nrPhone, String email) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.nrPhone = nrPhone;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}


