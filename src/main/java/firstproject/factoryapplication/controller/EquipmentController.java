package firstproject.factoryapplication.controller;


import firstproject.factoryapplication.model.Equipment;
import firstproject.factoryapplication.service.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public List<Equipment> getAll() {
        return equipmentService.findAll();
    }

    @GetMapping("/{id}")
    public Equipment getById(@PathVariable Long id) {
        return equipmentService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Equipment> create(@RequestBody Equipment equipment) {
        Equipment created = equipmentService.create(equipment);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Equipment> patchUpdate(
            @PathVariable Long id,
            @RequestBody Equipment updatedEquipment
    ) {
        Equipment updated = equipmentService.patchUpdate(id, updatedEquipment);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        equipmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public List<Equipment> getByStatus(@PathVariable String status) {
        return equipmentService.findByStatus(status);
    }

    @GetMapping("/type/{type}")
    public List<Equipment> getByType(@PathVariable String type) {
        return equipmentService.findByType(type);
    }
}

