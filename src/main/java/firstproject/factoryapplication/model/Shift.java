package firstproject.factoryapplication.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shift")
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;

    @ManyToMany(mappedBy = "shifts")
    @com.fasterxml.jackson.annotation.JsonIgnore
    @Builder.Default
    private List<Employee> employees = new ArrayList<>();

    @Override
    public String toString() {
        return "Shift{id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + "}";
    }
}
