package firstproject.factoryapplication.repository;

import java.util.Arrays;

public class Equipment {
    private int id;
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

    public int getId() {
        return id;
    }

    public double[] getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
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
