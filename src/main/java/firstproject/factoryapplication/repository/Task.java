package firstproject.factoryapplication.repository;

import java.time.LocalDate;

public class Task {
    private int id;
    private int laborCost;
    private LocalDate startTime;
    private LocalDate endTime;
    private Employee employee;
    private String priority;

    public Task() {
    }

    public Task(int id, int laborCost, LocalDate startTime, LocalDate endTime, Employee employee, String priority) {
        this.id = id;
        this.laborCost = laborCost;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public int getLaborCost() {
        return laborCost;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getPriority() {
        return priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLaborCost(int laborCost) {
        this.laborCost = laborCost;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", laborCost=" + laborCost +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", employee=" + employee +
                ", priority='" + priority + '\'' +
                '}';
    }
}
