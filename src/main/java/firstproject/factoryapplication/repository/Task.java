package firstproject.factoryapplication.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int laborCost;
    private LocalDate startTime;
    private LocalDate endTime;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String priority;

    @ManyToMany(mappedBy = "tasks")
    private List<ScheduleTask> scheduledTasks;

    public Task() {
    }

    public Task(int id, int laborCost, LocalDate startTime, LocalDate endTime, Employee employee, String priority) {
        this.id = id;
        this.laborCost = laborCost;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", laborCost=" + laborCost +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", employee=" + employee +
                ", priority='" + priority + '\'' +
                '}';
    }
}
