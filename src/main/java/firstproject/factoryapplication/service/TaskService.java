package firstproject.factoryapplication.service;

import firstproject.factoryapplication.model.Employee;
import firstproject.factoryapplication.model.ScheduleTask;
import firstproject.factoryapplication.model.Task;
import firstproject.factoryapplication.repository.EmployeeRepository;
import firstproject.factoryapplication.repository.ScheduleTaskRepository;
import firstproject.factoryapplication.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ScheduleTaskRepository scheduleTaskRepository;
    private final EmployeeRepository employeeRepository;

    public TaskService(TaskRepository taskRepository, ScheduleTaskRepository scheduleTaskRepository, EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.scheduleTaskRepository = scheduleTaskRepository;
        this.employeeRepository = employeeRepository;
    }

    public Task findById(long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByEmployee(long id) {
        return taskRepository.findByEmployeeId(id);
    }

    public void deleteById(long id) {
        taskRepository.deleteById((int) id);
    }

    public void deleteByEmployeeId(long id) {
        taskRepository.deleteByEmployeeId(id);
    }

    public void create(Task task) {
        Optional<Task> optionalTask = taskRepository.findByName(task.getName());
        if (optionalTask.isPresent()) {
            throw new IllegalStateException("Task already exists");
        }

        getPriority(task);
        Task savedTask = taskRepository.save(task);
        ScheduleTask scheduleTask = new ScheduleTask();
        scheduleTask.setTasks(List.of(savedTask));
        // сохраняем расписание
        scheduleTaskRepository.save(scheduleTask);
        // нужно вызвать метод который будет сортировать коллекцию
        /*List<Task> tasks = taskRepository.findAll();
        sortTasks(tasks);*/
    }

    // установка приоритета задаче
    private static void getPriority(Task task) {
        Duration duration = Duration.between(task.getStartTime(), task.getEndTime());
        Duration twoHours = Duration.ofHours(2);
        Duration oneHours = Duration.ofHours(1);
        if (duration.compareTo(twoHours) > 0) {
            task.setPriority("High");
        } else if( duration.compareTo(oneHours) < 0) {
            task.setPriority("Medium");
        } else {
            task.setPriority("Low");
        }
    }

    public Task assignTaskToEmployee(Long employeeId, Task task) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        task.setEmployee(employee);
        ScheduleTask scheduleTask = scheduleTaskRepository.findByEmployeeId(employeeId);
        scheduleTask.getTasks().add(task);

        return taskRepository.save(task);
    }


    public void update(Long id, LocalTime startTime, LocalTime endTime, Employee employee) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new IllegalStateException("Task not found");
        }

        Employee oldEmployee = task.getEmployee();
        ScheduleTask oldScheduleTask;
        if (oldEmployee == null) {
            oldScheduleTask = scheduleTaskRepository.findByEmployeeId(oldEmployee.getId());
        } else {
            oldScheduleTask = null;
        }

        // Удаляем задачу из старого расписания, если есть
        if (oldScheduleTask != null) {
            oldScheduleTask.getTasks().remove(task);
            scheduleTaskRepository.save(oldScheduleTask);
        }

        task.setStartTime(startTime);
        task.setEndTime(endTime);
        task.setEmployee(employee);
        getPriority(task);
        taskRepository.save(task);

        // Получаем расписание нового сотрудника
        ScheduleTask newSchedule = scheduleTaskRepository.findByEmployeeId(employee.getId());
        if (newSchedule == null) {
            newSchedule = new ScheduleTask();
            newSchedule.setEmployee(employee);
            newSchedule.setTasks(new ArrayList<>());
        }
        newSchedule.getTasks().add(task);
        newSchedule.setTasks(new ScheduleTaskService(scheduleTaskRepository).sortTasks(newSchedule.getTasks()));
        scheduleTaskRepository.save(newSchedule);
    }

    public List<Task> getTasksForEmployee(Long employeeId) {
        return taskRepository.findByEmployeeId(employeeId);
    }
}
