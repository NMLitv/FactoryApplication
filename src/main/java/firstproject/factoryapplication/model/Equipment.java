package firstproject.factoryapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Arrays;
import java.util.List;
import firstproject.factoryapplication.model.enums.EquipmentStatus;
import firstproject.factoryapplication.model.enums.EquipmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_x")
    private double locationX;

    @Column(name = "location_y")
    private double locationY;

    @Positive(message = "Capacity must be positive")
    private int capacity;

    @NotNull(message = "Status cannot be null")
    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;

    @NotNull(message = "Type cannot be null")
    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    // @JsonIgnore чтобы не было бесконечной рекурсии при сериализации
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Task> tasks = new ArrayList<>();

    @Override
    public String toString() {
        return "Equipment{id=" + id + ", type=" + type + ", status=" + status + "}";
    }
}

