package firstproject.factoryapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String position;
    private double salary;
    private String status;

    private Double[] location;

    @ManyToMany
    @JoinTable(
            name = "shift_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "shift_id")
    )
    private List<Shift> shifts;

    public Employee() {

    }



    public Employee(long id, String position, double salary, String status, Double[] location, List<Shift> shifts) {

        this.id = id;
        this.position = position;
        this.salary = salary;
        this.status = status;
        this.location = location;
        this.shifts = shifts;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", status='" + status + '\'' +
                ", location=" + Arrays.toString(location) +
                '}';
    }
}
