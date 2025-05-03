package firstproject.factoryapplication.repository;

import firstproject.factoryapplication.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findByName(String name);

    // найти все задачи по id сотрудника
    List<Task> findByEmployeeId(Long employeeId);

    // удалить задачи по id сотрудника
    void deleteByEmployeeId(Long employeeId);

    // переопределить, чтобы возвращать Task, а не Optional<Task>
    Task findById(Long id); // либо оставить Optional<Task>
}
