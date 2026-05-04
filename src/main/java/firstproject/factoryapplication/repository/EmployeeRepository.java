package firstproject.factoryapplication.repository;

import firstproject.factoryapplication.model.Employee;
import firstproject.factoryapplication.model.enums.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByStatus(EmployeeStatus status);
    Optional<Employee> findByPosition(String position);
}
