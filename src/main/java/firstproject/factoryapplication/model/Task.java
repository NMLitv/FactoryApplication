package firstproject.factoryapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalTime;
import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    private Equipment equipment;

    private String priority;

    @ManyToMany(mappedBy = "tasks")
    private List<ScheduleTask> scheduledTasks;

    public Task() {
    }

    public Task(Long id, LocalTime startTime, LocalTime endTime, Employee employee) {
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
