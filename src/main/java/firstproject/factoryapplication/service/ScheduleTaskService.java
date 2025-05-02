package firstproject.factoryapplication.service;

import firstproject.factoryapplication.model.ScheduleTask;
import firstproject.factoryapplication.model.Task;
import firstproject.factoryapplication.repository.ScheduleTaskRepository;

import java.util.List;


public class ScheduleTaskService {
    private final ScheduleTaskRepository scheduleTaskRepository;

    public ScheduleTaskService(ScheduleTaskRepository scheduleTaskRepository) {
        this.scheduleTaskRepository = scheduleTaskRepository;
    }

    public ScheduleTask findById(long id) {
        return scheduleTaskRepository.findById(id);
    }

    public void create(ScheduleTask scheduleTask) {
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
