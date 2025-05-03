package firstproject.factoryapplication.repository;


import firstproject.factoryapplication.model.ScheduleTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleTaskRepository extends JpaRepository<ScheduleTask, Integer> {

    ScheduleTask findByEmployeeId(Long id);

    ScheduleTask findById(Long id);
}
