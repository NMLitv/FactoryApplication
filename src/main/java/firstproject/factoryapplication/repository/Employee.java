package firstproject.factoryapplication.repository;

import java.util.Arrays;

public class Employee {
    private int id;
    private String position;
    private double salary;
    private String status;
    private double[] location;

    public Employee() {
    }

    public Employee(int id, String position, double salary, String status, double[] location) {
        this.id = id;
        this.position = position;
        this.salary = salary;
        this.status = status;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public String getStatus() {
        return status;
    }

    public double[] getLocation() {
        return location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", status='" + status + '\'' +
                ", location=" + Arrays.toString(location) +
                '}';
    }
}
