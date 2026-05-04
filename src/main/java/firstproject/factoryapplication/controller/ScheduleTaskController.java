package firstproject.factoryapplication.controller;

import firstproject.factoryapplication.dto.ScheduleTaskDto;
import firstproject.factoryapplication.model.ScheduleTask;
import firstproject.factoryapplication.service.ScheduleTaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleTaskController {

    private final ScheduleTaskService scheduleTaskService;

    @GetMapping
    public ResponseEntity<List<ScheduleTask>> getAll() {
        return ResponseEntity.ok(scheduleTaskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleTask> getById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleTaskService.findById(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<ScheduleTask> getByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(scheduleTaskService.findByEmployeeId(employeeId));
    }

    @PostMapping
    public ResponseEntity<ScheduleTask> create(@Valid @RequestBody ScheduleTaskDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleTaskService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleTask> update(@PathVariable Long id,
                                               @RequestBody ScheduleTaskDto dto) {
        return ResponseEntity.ok(scheduleTaskService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleTaskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<Void> deleteByEmployee(@PathVariable Long employeeId) {
        scheduleTaskService.deleteByEmployeeId(employeeId);
        return ResponseEntity.noContent().build();
    }
}