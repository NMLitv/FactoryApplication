package firstproject.factoryapplication.controller;

import firstproject.factoryapplication.model.Employee;
import firstproject.factoryapplication.model.Task;
import firstproject.factoryapplication.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskService.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public List<Task> findByEmployeeId(@PathVariable long employeeId) {
        return taskService.findByEmployee(employeeId);
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable long id) {
        return taskService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody Task task) {
        taskService.create(task);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        taskService.deleteById(id);
    }

    @DeleteMapping("/employee/{employeeId}")
    public void deleteByEmployeeId(@PathVariable long employeeId) {
        taskService.deleteByEmployeeId(employeeId);
    }

    @PutMapping("/{id}")
    public void update(
            @PathVariable Long id,
            @RequestParam LocalTime startTime,
            @RequestParam LocalTime endTime,
            @RequestBody Employee employee // можно заменить на employeeId + findById() внутри
    ) {
        taskService.update(id, startTime, endTime, employee);
    }
}
