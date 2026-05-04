package firstproject.factoryapplication.service;

import firstproject.factoryapplication.dto.ScheduleTaskDto;
import firstproject.factoryapplication.model.Employee;
import firstproject.factoryapplication.model.ScheduleTask;
import firstproject.factoryapplication.model.Task;
import firstproject.factoryapplication.repository.EmployeeRepository;
import firstproject.factoryapplication.repository.ScheduleTaskRepository;
import firstproject.factoryapplication.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// ===== ScheduleTaskService.java =====
@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleTaskService {

    private final ScheduleTaskRepository scheduleTaskRepository;
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskService taskService;

    @Transactional(readOnly = true)
    public List<ScheduleTask> findAll() {
        return scheduleTaskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ScheduleTask findById(Long id) {
        return scheduleTaskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ScheduleTask not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public ScheduleTask findByEmployeeId(Long employeeId) {
        return scheduleTaskRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found for employee: " + employeeId));
    }

    @Transactional
    public ScheduleTask create(ScheduleTaskDto dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        List<Task> tasks = new ArrayList<>();
        if (dto.getTaskIds() != null) {
            tasks = new ArrayList<>(taskRepository.findAllById(dto.getTaskIds()));
        }

        taskService.sortTasksByPriority(tasks);

        ScheduleTask schedule = ScheduleTask.builder()
                .employee(employee)
                .tasks(tasks)
                .build();

        log.info("Created ScheduleTask for employee {}", employee.getId());
        return scheduleTaskRepository.save(schedule);
    }

    @Transactional
    public ScheduleTask update(Long id, ScheduleTaskDto dto) {
        ScheduleTask schedule = findById(id);

        if (dto.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
            schedule.setEmployee(employee);
        }

        if (dto.getTaskIds() != null) {
            List<Task> tasks = new ArrayList<>(taskRepository.findAllById(dto.getTaskIds()));
            taskService.sortTasksByPriority(tasks);
            schedule.setTasks(tasks);
        }

        log.info("Updated ScheduleTask with id: {}", id);
        return scheduleTaskRepository.save(schedule);
    }

    @Transactional
    public void deleteByEmployeeId(Long employeeId) {
        scheduleTaskRepository.findByEmployeeId(employeeId).ifPresent(schedule -> {
            scheduleTaskRepository.delete(schedule);
            log.info("Deleted schedule for employee {}", employeeId);
        });
    }

    @Transactional
    public void delete(Long id) {
        if (!scheduleTaskRepository.existsById(id)) {
            throw new EntityNotFoundException("ScheduleTask not found with id: " + id);
        }
        scheduleTaskRepository.deleteById(id);
        log.info("Deleted ScheduleTask with id: {}", id);
    }

    /**
     * Делегирует сортировку в TaskService — единое место логики приоритетов.
     * Вызывается из TaskService.update() через инжектированный бин.
     */
    public List<Task> sortTasks(List<Task> tasks) {
        taskService.sortTasksByPriority(tasks);
        return tasks;
    }
}