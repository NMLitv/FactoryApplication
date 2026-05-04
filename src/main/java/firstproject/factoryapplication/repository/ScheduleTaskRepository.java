package firstproject.factoryapplication.repository;


import firstproject.factoryapplication.model.ScheduleTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleTaskRepository extends JpaRepository<ScheduleTask, Long> {
    Optional<ScheduleTask> findByEmployeeId(Long employeeId);
    void deleteByEmployeeId(Long employeeId);
}
