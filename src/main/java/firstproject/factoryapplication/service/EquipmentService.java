package firstproject.factoryapplication.service;

import firstproject.factoryapplication.dto.EquipmentDto;
import firstproject.factoryapplication.model.Equipment;
import firstproject.factoryapplication.model.enums.EquipmentStatus;
import firstproject.factoryapplication.model.enums.EquipmentType;
import firstproject.factoryapplication.repository.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Transactional(readOnly = true)
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Equipment findById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found with id: " + id));
    }

    @Transactional
    public Equipment create(EquipmentDto dto) {
        Equipment equipment = Equipment.builder()
                .locationX(dto.getLocationX())
                .locationY(dto.getLocationY())
                .capacity(dto.getCapacity())
                .status(dto.getStatus())
                .type(dto.getType())
                .build();
        log.info("Created equipment of type: {}", dto.getType());
        return equipmentRepository.save(equipment);
    }

    @Transactional
    public Equipment patch(Long id, EquipmentDto dto) {
        Equipment existing = findById(id);
        if (dto.getLocationX() != 0) existing.setLocationX(dto.getLocationX());
        if (dto.getLocationY() != 0) existing.setLocationY(dto.getLocationY());
        if (dto.getCapacity() != 0) existing.setCapacity(dto.getCapacity());
        if (dto.getStatus() != null) existing.setStatus(dto.getStatus());
        if (dto.getType() != null) existing.setType(dto.getType());
        log.info("Patched equipment with id: {}", id);
        return equipmentRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!equipmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Equipment not found with id: " + id);
        }
        equipmentRepository.deleteById(id);
        log.info("Deleted equipment with id: {}", id);
    }

    @Transactional(readOnly = true)
    public List<Equipment> findByStatus(EquipmentStatus status) {
        return equipmentRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<Equipment> findByType(EquipmentType type) {
        return equipmentRepository.findByType(type);
    }
}
