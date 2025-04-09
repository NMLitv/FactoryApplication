package firstproject.factoryapplication.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double[] location;
    private int capacity;
    private String status;
    private String type;

    public Equipment() {
    }

    public Equipment(int id, double[] location, int capacity, String status, String type) {
        this.id = id;
        this.location = location;
        this.capacity = capacity;
        this.status = status;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
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

