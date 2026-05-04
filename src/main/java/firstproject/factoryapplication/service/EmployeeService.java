package firstproject.factoryapplication.service;

import firstproject.factoryapplication.dto.EmployeeDto;
import firstproject.factoryapplication.model.Employee;
import firstproject.factoryapplication.model.enums.EmployeeStatus;
import firstproject.factoryapplication.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public Employee create(EmployeeDto dto) {
        log.info("Creating employee with position: {}", dto.getPosition());
        Employee employee = Employee.builder()
                .position(dto.getPosition())
                .salary(dto.getSalary())
                .status(dto.getStatus())
                .locationX(dto.getLocationX())
                .locationY(dto.getLocationY())
                .build();
        return employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Employee> findByStatus(EmployeeStatus status) {
        return employeeRepository.findByStatus(status);
    }

    @Transactional
    public Employee update(Long id, EmployeeDto dto) {
        Employee employee = findById(id);
        employee.setPosition(dto.getPosition());
        employee.setSalary(dto.getSalary());
        employee.setStatus(dto.getStatus());
        employee.setLocationX(dto.getLocationX());
        employee.setLocationY(dto.getLocationY());
        log.info("Updated employee with id: {}", id);
        return employeeRepository.save(employee);
    }

    @Transactional
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with id: " + id);
        }
        log.info("Deleting employee with id: {}", id);
        employeeRepository.deleteById(id);
    }
}