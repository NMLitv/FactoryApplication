package firstproject.factoryapplication.controller;

import firstproject.factoryapplication.dto.EquipmentDto;
import firstproject.factoryapplication.model.Equipment;
import firstproject.factoryapplication.model.enums.EquipmentStatus;
import firstproject.factoryapplication.model.enums.EquipmentType;
import firstproject.factoryapplication.service.EquipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<Equipment>> getAll() {
        return ResponseEntity.ok(equipmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(equipmentService.findById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Equipment>> getByStatus(@PathVariable EquipmentStatus status) {
        return ResponseEntity.ok(equipmentService.findByStatus(status));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Equipment>> getByType(@PathVariable EquipmentType type) {
        return ResponseEntity.ok(equipmentService.findByType(type));
    }

    @PostMapping
    public ResponseEntity<Equipment> create(@Valid @RequestBody EquipmentDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentService.create(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Equipment> patch(@PathVariable Long id,
                                           @RequestBody EquipmentDto dto) {
        return ResponseEntity.ok(equipmentService.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        equipmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}