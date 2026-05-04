package firstproject.factoryapplication.service;

import firstproject.factoryapplication.dto.ShiftDto;
import firstproject.factoryapplication.model.Employee;
import firstproject.factoryapplication.model.Shift;
import firstproject.factoryapplication.repository.EmployeeRepository;
import firstproject.factoryapplication.repository.ShiftRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<Shift> findAll() {
        return shiftRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Shift findById(Long id) {
        return shiftRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shift not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Shift> findByEmployeeId(Long employeeId) {
        return shiftRepository.findByEmployeeId(employeeId);
    }

    @Transactional
    public Shift create(ShiftDto dto) {
        Shift shift = Shift.builder()
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .build();
        log.info("Created shift {} - {}", dto.getStartTime(), dto.getEndTime());
        return shiftRepository.save(shift);
    }

    @Transactional
    public void delete(Long id) {
        if (!shiftRepository.existsById(id)) {
            throw new EntityNotFoundException("Shift not found with id: " + id);
        }
        shiftRepository.deleteById(id);
        log.info("Deleted shift with id: {}", id);
    }

    @Transactional
    public Shift addEmployee(Long shiftId, Long employeeId) {
        Shift shift = findById(shiftId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));

        if (shift.getEmployees().contains(employee)) {
            throw new IllegalStateException("Employee " + employeeId + " is already in shift " + shiftId);
        }

        shift.getEmployees().add(employee);
        employee.getShifts().add(shift);

        log.info("Added employee {} to shift {}", employeeId, shiftId);
        return shiftRepository.save(shift);
    }

    @Transactional
    public Shift removeEmployee(Long shiftId, Long employeeId) {
        Shift shift = findById(shiftId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        shift.getEmployees().remove(employee);
        employee.getShifts().remove(shift);

        return shiftRepository.save(shift);
    }
}