package firstproject.factoryapplication.service;

import firstproject.factoryapplication.model.Employee;
import firstproject.factoryapplication.model.Task;
import firstproject.factoryapplication.model.enums.EmployeeStatus;
import firstproject.factoryapplication.repository.EmployeeRepository;
import firstproject.factoryapplication.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

// ===== TaskAssignmentService.java =====
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskAssignmentService {

    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    @Transactional
    public List<Task> autoAssign() {
        List<Employee> freeEmployees = new ArrayList<>(
                employeeRepository.findByStatus(EmployeeStatus.FREE)
        );
        if (freeEmployees.isEmpty()) {
            log.warn("No free employees available for assignment");
            return List.of();
        }

        List<Task> unassignedTasks = taskRepository.findByEmployeeIsNull();
        taskService.sortTasksByPriority(unassignedTasks); // HIGH задачи назначаются первыми

        List<Task> assigned = new ArrayList<>();

        for (Task task : unassignedTasks) {
            if (freeEmployees.isEmpty()) break;

            if (task.getEquipment() == null) {
                log.warn("Task {} has no equipment, skipping", task.getId());
                continue;
            }

            Employee nearest = findNearestEmployee(task, freeEmployees);
            task.setEmployee(nearest);
            taskService.calculatePriority(task); // void — просто вызываем, не присваиваем
            taskRepository.save(task);

            freeEmployees.remove(nearest); // сотрудник занят, убираем из пула
            assigned.add(task);

            log.info("Auto-assigned task {} to employee {}", task.getId(), nearest.getId());
        }

        return assigned;
    }

    private Employee findNearestEmployee(Task task, List<Employee> employees) {
        double eqX = task.getEquipment().getLocationX();
        double eqY = task.getEquipment().getLocationY();

        return employees.stream()
                .min((a, b) -> Double.compare(
                        distance(eqX, eqY, a.getLocationX(), a.getLocationY()),
                        distance(eqX, eqY, b.getLocationX(), b.getLocationY())
                ))
                .orElseThrow(() -> new EntityNotFoundException("No employees available"));
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
    }
}