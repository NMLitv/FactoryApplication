package firstproject.factoryapplication.controller;

import firstproject.factoryapplication.dto.EmployeeDto;
import firstproject.factoryapplication.model.Employee;
import firstproject.factoryapplication.model.Task;
import firstproject.factoryapplication.model.enums.EmployeeStatus;
import firstproject.factoryapplication.service.EmployeeService;
import firstproject.factoryapplication.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// ===== EmployeeController.java =====
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Employee>> getByStatus(@PathVariable EmployeeStatus status) {
        return ResponseEntity.ok(employeeService.findByStatus(status));
    }

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody EmployeeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id,
                                           @Valid @RequestBody EmployeeDto dto) {
        return ResponseEntity.ok(employeeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{employeeId}/tasks")
    public ResponseEntity<List<Task>> getTasks(@PathVariable Long employeeId) {
        return ResponseEntity.ok(taskService.findByEmployee(employeeId));
    }

    // имя метода приведено в соответствие с TaskService
    @PostMapping("/{employeeId}/tasks/{taskId}")
    public ResponseEntity<Task> assignTask(@PathVariable Long employeeId,
                                           @PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.assignTaskToEmployee(employeeId,
                taskService.findById(taskId)
                        .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId))));
    }
}