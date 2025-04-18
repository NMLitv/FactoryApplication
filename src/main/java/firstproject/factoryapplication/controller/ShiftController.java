package firstproject.factoryapplication.controller;

import firstproject.factoryapplication.model.Shift;
import firstproject.factoryapplication.service.ShiftService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/shift")
public class ShiftController {
    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping
    public void getAll(){
        shiftService.findAll();
    }

    /*@GetMapping("/employee/{employeeId}")
    public void getShiftByEmployeeId(Long employeeId){
        shiftService.getShiftByEmployeeId(employeeId);
    }*/

    @PostMapping
    public void create(@RequestBody Shift shift){
        shiftService.create(shift);
    }

    @DeleteMapping("/{shiftId}")
    public void deleteEmployeeFromShift(Long shiftId){
        shiftService.delete(shiftId);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Shift> update(@PathVariable Long id,
                                        @RequestBody Long employeeId){
        Shift newShift = shiftService.addEmployee(id, employeeId);
        return ResponseEntity.ok(newShift);
    }
}
