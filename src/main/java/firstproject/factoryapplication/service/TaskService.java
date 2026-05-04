package firstproject.factoryapplication.service;

import firstproject.factoryapplication.dto.TaskDto;
import firstproject.factoryapplication.dto.TaskUpdateRequest;
import firstproject.factoryapplication.model.Employee;
import firstproject.factoryapplication.model.Equipment;
import firstproject.factoryapplication.model.ScheduleTask;
import firstproject.factoryapplication.model.Task;
import firstproject.factoryapplication.model.enums.TaskPriority;
import firstproject.factoryapplication.repository.EmployeeRepository;
import firstproject.factoryapplication.repository.EquipmentRepository;
import firstproject.factoryapplication.repository.ScheduleTaskRepository;
import firstproject.factoryapplication.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// ===== TaskService.java =====
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ScheduleTaskRepository scheduleTaskRepository;
    private final EmployeeRepository employeeRepository;
    private final EquipmentRepository equipmentRepository;
    private final ScheduleTaskService scheduleTaskService;

    public TaskService(TaskRepository taskRepository,
                       ScheduleTaskRepository scheduleTaskRepository,
                       EmployeeRepository employeeRepository,
                       EquipmentRepository equipmentRepository,
                       @Lazy ScheduleTaskService scheduleTaskService) {
        this.taskRepository = taskRepository;
        this.scheduleTaskRepository = scheduleTaskRepository;
        this.employeeRepository = employeeRepository;
        this.equipmentRepository = equipmentRepository;
        this.scheduleTaskService = scheduleTaskService;
    }

    public Optional<Task> findById(long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByEmployee(long id) {
        return taskRepository.findByEmployeeId(id);
    }

    @Transactional
    public Task create(TaskDto dto) {
        taskRepository.findByName(dto.getName()).ifPresent(t -> {
            throw new IllegalStateException("Task with name '" + dto.getName() + "' already exists");
        });

        Task task = new Task();
        task.setName(dto.getName());
        task.setStartTime(dto.getStartTime());
        task.setEndTime(dto.getEndTime());

        if (dto.getEquipmentId() != null) {
            Equipment equipment = equipmentRepository.findById(dto.getEquipmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Equipment not found"));
            task.setEquipment(equipment);
        }

        calculatePriority(task); // устанавливаем приоритет
        Task savedTask = taskRepository.save(task);

        ScheduleTask scheduleTask = new ScheduleTask();
        scheduleTask.setTasks(new ArrayList<>(List.of(savedTask)));
        scheduleTaskRepository.save(scheduleTask);

        return savedTask;
    }

    @Transactional
    public Task update(Long id, TaskUpdateRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));

        Employee newEmployee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + request.getEmployeeId()));

        Employee oldEmployee = task.getEmployee();
        if (oldEmployee != null) {
            scheduleTaskRepository.findByEmployeeId(oldEmployee.getId()).ifPresent(oldSchedule -> {
                oldSchedule.getTasks().remove(task);
                scheduleTaskRepository.save(oldSchedule);
            });
        }

        task.setStartTime(request.getStartTime());
        task.setEndTime(request.getEndTime());
        task.setEmployee(newEmployee);
        calculatePriority(task);
        taskRepository.save(task);

        ScheduleTask newSchedule = scheduleTaskRepository.findByEmployeeId(newEmployee.getId())
                .orElseGet(() -> {
                    ScheduleTask s = new ScheduleTask();
                    s.setEmployee(newEmployee);
                    s.setTasks(new ArrayList<>());
                    return s;
                });

        newSchedule.getTasks().add(task);
        newSchedule.setTasks(scheduleTaskService.sortTasks(newSchedule.getTasks()));
        scheduleTaskRepository.save(newSchedule);

        return task;
    }

    @Transactional
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    @Transactional
    public Task assignTaskToEmployee(long employeeId, Task task) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));

        task.setEmployee(employee);

        ScheduleTask scheduleTask = scheduleTaskRepository.findByEmployeeId(employeeId)
                .orElseGet(() -> {
                    ScheduleTask s = new ScheduleTask();
                    s.setEmployee(employee);
                    s.setTasks(new ArrayList<>());
                    return s;
                });

        scheduleTask.getTasks().add(task);
        scheduleTaskRepository.save(scheduleTask);
        return taskRepository.save(task);
    }

    public List<Task> getTasksForEmployee(long employeeId) {
        return taskRepository.findByEmployeeId(employeeId);
    }

    /**
     * Вычисляет и устанавливает приоритет задаче на основе её длительности:
     * < 1 часа  — HIGH   (срочно)
     * 1–2 часа  — MEDIUM
     * > 2 часов — LOW
     */
    public void calculatePriority(Task task) {
        Duration duration = Duration.between(task.getStartTime(), task.getEndTime());
        if (duration.compareTo(Duration.ofHours(1)) < 0) {
            task.setPriority(TaskPriority.HIGH);
        } else if (duration.compareTo(Duration.ofHours(2)) <= 0) {
            task.setPriority(TaskPriority.MEDIUM);
        } else {
            task.setPriority(TaskPriority.LOW);
        }
    }

    /**
     * Сортирует задачи по приоритету — HIGH первым, LOW последним
     */
    public void sortTasksByPriority(List<Task> tasks) {
        tasks.sort((a, b) -> {
            int pa = a.getPriority() != null ? a.getPriority().getValue() : 0;
            int pb = b.getPriority() != null ? b.getPriority().getValue() : 0;
            return Integer.compare(pb, pa);
        });
    }
}