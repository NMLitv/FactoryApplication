package firstproject.factoryapplication.service;

import firstproject.factoryapplication.model.Employee;
import firstproject.factoryapplication.model.ScheduleTask;
import firstproject.factoryapplication.model.Task;
import firstproject.factoryapplication.repository.EmployeeRepository;
import firstproject.factoryapplication.repository.ScheduleTaskRepository;
import firstproject.factoryapplication.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleTaskService {
    private final ScheduleTaskRepository scheduleTaskRepository;
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    public ScheduleTaskService(ScheduleTaskRepository scheduleTaskRepository, TaskRepository taskRepository, EmployeeRepository employeeRepository) {
        this.scheduleTaskRepository = scheduleTaskRepository;
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<ScheduleTask> findAll() {
        return scheduleTaskRepository.findAll();
    }

    public ScheduleTask findById(long id) {
        return scheduleTaskRepository.findById(id);
    }

    public void deleteByEmployeeId(long employeeId) {
        scheduleTaskRepository.findByEmployeeId(employeeId);
    }

    public void create(ScheduleTask scheduleTask) {
        scheduleTaskRepository.save(scheduleTask);
    }

    public void update(long id, long taskId, long employeeId) {
        ScheduleTask scheduleTask = scheduleTaskRepository.findById(id);
        if (scheduleTask == null) {
            throw new IllegalStateException("ScheduleTask not found");
        }

        Task task = taskRepository.findById(taskId);
        if (task == null) {
            throw new IllegalStateException("Task not found");
        }

        Employee employee = employeeRepository.findEmployeeById(employeeId);
        if (employee == null) {
            throw new IllegalStateException("Employee not found");
        }

        // Обновим список задач (например, просто заменим одной задачей)
        scheduleTask.setTasks(List.of(task));
        scheduleTask.setEmployee(employee);

        scheduleTaskRepository.save(scheduleTask);
    }

    // сортировка пузырьком
    public List<Task> sortTasks(List<Task> taskList) {
        for (int i = 0; i < taskList.size() - 1; i++) {
            for (int j = 0; j < taskList.size() - i - 1; j++) {
                int currentPriority = getPriorityValue(taskList.get(j).getPriority());
                int nextPriority = getPriorityValue(taskList.get(j + 1).getPriority());
                if (currentPriority < nextPriority) {
                    Task temp = taskList.get(j);
                    taskList.set(j, taskList.get(j + 1));
                    taskList.set(j + 1, temp);
                }
            }
        }
        return taskList;
    }

    private int getPriorityValue(String priority) {
        return switch (priority.toUpperCase()) {
            case "HIGH" -> 3;
            case "MEDIUM" -> 2;
            case "LOW" -> 1;
            default -> 0;
        };
    }
}
