package firstproject.factoryapplication.service;

import firstproject.factoryapplication.model.Employee;
import firstproject.factoryapplication.model.Shift;
import firstproject.factoryapplication.repository.EmployeeRepository;
import firstproject.factoryapplication.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ShiftService {
    private final ShiftRepository shiftRepository;
    private final EmployeeRepository employeeRepository;

    public ShiftService(ShiftRepository shiftRepository, EmployeeRepository employeeRepository) {
        this.shiftRepository = shiftRepository;
        this.employeeRepository = employeeRepository;
    }

    /*public List<Shift> getShiftByEmployeeId(Long employeeId) {
        return shiftRepository.findByEmployeeId(employeeId);
    }*/

    public void findAll() {
        shiftRepository.findAll().forEach(System.out::println);
    }

    public void create(Shift shift) {
        shiftRepository.save(shift);
    }

    public void delete(Long shiftId) {
        shiftRepository.findById(shiftId);
    }

    public Shift addEmployee(Long id, Long employeeId) {
        Optional<Shift> oldShift = shiftRepository.findById(id);
        Shift shift = oldShift.orElse(new Shift()); // если не найден, создаём новый
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            shift.getEmployees().add(optionalEmployee.get());
        } else {
            System.out.println("Сотрудник не найден");
        }
        return shift;
    }
}
