package firstproject.factoryapplication.repository;
import firstproject.factoryapplication.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeById(long id);
}
