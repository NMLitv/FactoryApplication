package firstproject.factoryapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;
import java.util.List;
import firstproject.factoryapplication.model.enums.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Task name cannot be blank")
    private String name;

    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    // mappedBy — эта сторона не владеет связью, @JsonIgnore рвёт цикл
    @ManyToMany(mappedBy = "tasks")
    @com.fasterxml.jackson.annotation.JsonIgnore
    @Builder.Default
    private List<ScheduleTask> scheduledTasks = new ArrayList<>();

    @Override
    public String toString() {
        return "Task{id=" + id + ", name='" + name + "', priority=" + priority + "}";
    }
}
