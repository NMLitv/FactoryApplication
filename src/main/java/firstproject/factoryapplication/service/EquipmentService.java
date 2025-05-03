package firstproject.factoryapplication.service;

import firstproject.factoryapplication.model.Equipment;
import firstproject.factoryapplication.repository.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    public Equipment findById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found with id: " + id));
    }

    public Equipment create(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment patchUpdate(Long id, Equipment updatedEquipment) {
        Equipment existing = findById(id);

        if (updatedEquipment.getLocation() != null) {
            existing.setLocation(updatedEquipment.getLocation());
        }
        if (updatedEquipment.getCapacity() != 0) {
            existing.setCapacity(updatedEquipment.getCapacity());
        }
        if (updatedEquipment.getStatus() != null) {
            existing.setStatus(updatedEquipment.getStatus());
        }
        if (updatedEquipment.getType() != null) {
            existing.setType(updatedEquipment.getType());
        }

        return equipmentRepository.save(existing);
    }

    public void deleteById(Long id) {
        equipmentRepository.deleteById(id);
    }

    public List<Equipment> findByStatus(String status) {
        return equipmentRepository.findByStatus(status);
    }

    public List<Equipment> findByType(String type) {
        return equipmentRepository.findByType(type);
    }
}
