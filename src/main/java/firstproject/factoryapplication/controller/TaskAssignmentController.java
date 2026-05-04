package firstproject.factoryapplication.controller;

import firstproject.factoryapplication.model.Task;
import firstproject.factoryapplication.service.TaskAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class TaskAssignmentController {

    private final TaskAssignmentService taskAssignmentService;

    /**
     * POST /assignments/auto
     * Автоматически назначает все незакреплённые задачи ближайшим свободным сотрудникам
     */
    @PostMapping("/auto")
    public ResponseEntity<List<Task>> autoAssign() {
        return ResponseEntity.ok(taskAssignmentService.autoAssign());
    }
}
