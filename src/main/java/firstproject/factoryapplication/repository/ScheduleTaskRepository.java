package firstproject.factoryapplication.repository;


import firstproject.factoryapplication.model.ScheduleTask;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleTaskRepository {

    ScheduleTask findById(long id);

    void save(ScheduleTask scheduleTask);

    void delete(int employeeId);

    List<ScheduleTask> findAll();

    ScheduleTask findByEmployeeId(long id);
}
