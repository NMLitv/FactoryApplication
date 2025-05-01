package firstproject.factoryapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double[] location;
    private int capacity;
    private String status;
    private String type;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public Equipment() {
    }

    public Equipment(Long id, double[] location, int capacity, String status, String type) {
        this.id = id;
        this.location = location;
        this.capacity = capacity;
        this.status = status;
        this.type = type;
    }



    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", location=" + Arrays.toString(location) +
                ", capacity=" + capacity +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

