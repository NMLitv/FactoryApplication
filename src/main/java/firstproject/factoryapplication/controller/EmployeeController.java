package firstproject.factoryapplication.controller;



import firstproject.factoryapplication.model.Employee;
import firstproject.factoryapplication.model.Task;
import firstproject.factoryapplication.service.EmployeeService;
import firstproject.factoryapplication.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/Employee")
public class EmployeeController {
    private final EmployeeService employeeService; // Сервис для работы с работниками
    private final TaskService taskService;// Сервис для работы с задачами

    public EmployeeController(EmployeeService employeeService, TaskService taskService) {
        this.employeeService = employeeService;
        this.taskService = taskService;
    }

    // Метод для создания рабочего
    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(createdEmployee);
    }

    // Метод для назначения задания рабочему
    @PostMapping("/{employeeId}/tasks")
    public ResponseEntity<Task> assignTaskToEmployee(@PathVariable Long employeeId, @RequestBody Task task) {
        Task assignedTask = taskService.assignTaskToEmployee(employeeId, task);
        return ResponseEntity.ok(assignedTask);
    }

    // Метод для получения всех задач рабочего
    @GetMapping("/{employeeId}/tasks")
    public ResponseEntity<List<Task>> getTasksForEmployee(@PathVariable Long employeeId) {
        List<Task> tasks = taskService.getTasksForEmployee(employeeId);
        return ResponseEntity.ok(tasks);
    }



}
