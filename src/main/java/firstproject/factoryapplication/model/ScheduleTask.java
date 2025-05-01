package firstproject.factoryapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "scheduled_task")
public class ScheduleTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "scheduled_task_task", // имя промежуточной таблицы
            joinColumns = @JoinColumn(name = "scheduled_task_id"), // имя столбца, указывающего на ScheduledTask
            inverseJoinColumns = @JoinColumn(name = "task_id") // имя столбца, указывающего на Task
    )
    private List<Task> tasks;

    @ManyToOne
    private Employee employee;

    public ScheduleTask() {
    }

    public ScheduleTask(Long id, List<Task> tasks, Employee employee) {
        this.id = id;
        this.tasks = tasks;
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "SheduleTask{" +
                "id=" + id +
                ", tasks=" + tasks +
                ", employee=" + employee +
                '}';
    }
}
