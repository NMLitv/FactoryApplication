package firstproject.factoryapplication.repository;

import firstproject.factoryapplication.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    @Query("SELECT s FROM Shift s JOIN s.employees e WHERE e.id = :employeeId")
    List<Shift> findByEmployeeId(Long employeeId);
}
