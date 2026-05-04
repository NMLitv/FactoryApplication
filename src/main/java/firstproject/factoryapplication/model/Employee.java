package firstproject.factoryapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Arrays;
import java.util.List;
import firstproject.factoryapplication.model.enums.EmployeeStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Position cannot be blank")
    private String position;

    @Positive(message = "Salary must be positive")
    private double salary;

    @NotNull(message = "Status cannot be null")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    // Координаты как два отдельных поля — JPA не умеет хранить double[]
    @Column(name = "location_x")
    private double locationX;

    @Column(name = "location_y")
    private double locationY;

    @ManyToMany
    @JoinTable(
            name = "shift_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "shift_id")
    )
  
    @Builder.Default
    private List<Shift> shifts = new ArrayList<>();

    @Override
    public String toString() {
        return "Employee{id=" + id + ", position='" + position + "', status=" + status + "}";
    }
}