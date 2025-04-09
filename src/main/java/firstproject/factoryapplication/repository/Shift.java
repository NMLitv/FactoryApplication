package firstproject.factoryapplication.repository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@Entity
public class Shift {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // генерациям id занимается сама БД
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    @ManyToMany(mappedBy = "shifts")
    private List<Employee> employees;

    public Shift() {
    }

    public Shift(long id, LocalTime startTime, LocalTime endTime, List<Employee> employees) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", employees=" + employees +
                '}';
    }
}
