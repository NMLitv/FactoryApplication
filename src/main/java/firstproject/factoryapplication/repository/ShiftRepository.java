package firstproject.factoryapplication.repository;

import firstproject.factoryapplication.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShiftRepository extends JpaRepository<Shift, Long> {

}
