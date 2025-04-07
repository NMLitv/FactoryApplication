package firstproject.factoryapplication.repository;

import java.util.List;

public class SheduleTask {
    private int id;
    List<Task> tasks;
    Employee employee;

    public SheduleTask() {
    }

    public SheduleTask(int id, List<Task> tasks, Employee employee) {
        this.id = id;
        this.tasks = tasks;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
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
