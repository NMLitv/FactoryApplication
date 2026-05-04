package firstproject.factoryapplication.controller;

import firstproject.factoryapplication.dto.ShiftDto;
import firstproject.factoryapplication.model.Shift;
import firstproject.factoryapplication.service.ShiftService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shifts")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;

    @GetMapping
    public ResponseEntity<List<Shift>> getAll() {
        return ResponseEntity.ok(shiftService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shift> getById(@PathVariable Long id) {
        return ResponseEntity.ok(shiftService.findById(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Shift>> getByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(shiftService.findByEmployeeId(employeeId));
    }

    @PostMapping
    public ResponseEntity<Shift> create(@Valid @RequestBody ShiftDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shiftService.create(dto));
    }

    @PostMapping("/{shiftId}/employees/{employeeId}")
    public ResponseEntity<Shift> addEmployee(@PathVariable Long shiftId,
                                             @PathVariable Long employeeId) {
        return ResponseEntity.ok(shiftService.addEmployee(shiftId, employeeId));
    }

    @DeleteMapping("/{shiftId}/employees/{employeeId}")
    public ResponseEntity<Shift> removeEmployee(@PathVariable Long shiftId,
                                                @PathVariable Long employeeId) {
        return ResponseEntity.ok(shiftService.removeEmployee(shiftId, employeeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        shiftService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
