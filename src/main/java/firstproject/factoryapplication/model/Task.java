package firstproject.factoryapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
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
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String priority;

    @ManyToMany(mappedBy = "tasks")
    private List<ScheduleTask> scheduledTasks;

    public Task() {
    }

    public Task(int id, LocalTime startTime, LocalTime endTime, Employee employee) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", employee=" + employee +
                ", priority='" + priority + '\'' +
                '}';
    }
}
