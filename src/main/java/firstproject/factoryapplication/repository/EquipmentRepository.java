package firstproject.factoryapplication.repository;

import firstproject.factoryapplication.model.Equipment;
import firstproject.factoryapplication.model.enums.EquipmentStatus;
import firstproject.factoryapplication.model.enums.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByStatus(EquipmentStatus status);
    List<Equipment> findByType(EquipmentType type);
}
