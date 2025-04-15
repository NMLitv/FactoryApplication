package firstproject.factoryapplication.repository;

import firstproject.factoryapplication.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findByName(String name);

    Task findById(long id);

    List<Task> findByPriority(String priority);

    List<Task> findByEmployeeId(long employee_id);

    List<Task> findByStartTimeAndEndTime(LocalTime startTime, LocalTime endTime);

    void deleteById(long id);

    void deleteByEmployeeId(long employee_id);
}
