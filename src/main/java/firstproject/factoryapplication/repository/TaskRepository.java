package firstproject.factoryapplication.repository;

import firstproject.factoryapplication.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByEmployeeId(Long employeeId);
    Optional<Task> findByName(String name);
    List<Task> findByEmployeeIsNull();
    void deleteByEmployeeId(Long employeeId);
}
