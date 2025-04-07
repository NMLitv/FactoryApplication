package firstproject.factoryapplication.repository;

import java.time.LocalTime;
import java.util.List;

public class Shift {
    private int id;
    private LocalTime startTime;
    private LocalTime endTime;
    List<Employee> employees;

    public Shift() {
    }

    public Shift(int id, LocalTime startTime, LocalTime endTime, List<Employee> employees) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", employees=" + employees +
                '}';
    }
}
