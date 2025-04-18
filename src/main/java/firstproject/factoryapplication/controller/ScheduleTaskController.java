package firstproject.factoryapplication.controller;


import firstproject.factoryapplication.model.ScheduleTask;
import firstproject.factoryapplication.service.ScheduleTaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduleTask")
public class ScheduleTaskController {
    private final ScheduleTaskService scheduleTaskService;

    public ScheduleTaskController(ScheduleTaskService scheduleTaskService) {
        this.scheduleTaskService = scheduleTaskService;
    }

    @GetMapping
    public ScheduleTask findAll(){
        return scheduleTaskService.findAll();
    }

    @GetMapping("{id}")
    public ScheduleTask getById(@PathVariable long id) {
        return scheduleTaskService.findById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public void getByEmployeeId(@PathVariable long employeeId) {
        scheduleTaskService.deleteByEmployeeId(employeeId);
    }

    @PostMapping
    public void create(@RequestBody ScheduleTask scheduleTask) {
        scheduleTaskService.create(scheduleTask);
    }

    @DeleteMapping("/employee/{employeeId}")
    public void deleteByEmployeeId(@PathVariable long employeeId) {
        scheduleTaskService.deleteByEmployeeId(employeeId);
    }

    @PutMapping("{id}")
    public void update(
            @PathVariable long id,
            @RequestParam long taskId,
            @RequestParam long EmployeeId
            ) {
        scheduleTaskService.update(id, taskId, EmployeeId);
    }
}
