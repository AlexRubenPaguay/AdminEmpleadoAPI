package com.adminempleado.Controller;

import com.adminempleado.DTO.EmployeeDTO;
import com.adminempleado.Service.IEmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/Employee/")
public class EmployeeController {
    @Autowired
    private IEmployeeService _serviceEmployee;

    @GetMapping("allEmployees")
    public ResponseEntity<List<EmployeeDTO>> getEmployee() {
        return _serviceEmployee.getAllEmployees();
    }

    @PostMapping("create")
    public void createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        _serviceEmployee.postEmployee(employeeDTO);
    }

    @PutMapping("update/{idEmployee}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @PathVariable @Min(value = 1, message = "The ID must be greater than zero.") long idEmployee, @Valid @RequestBody EmployeeDTO employeeDTO) {
        return _serviceEmployee.updateEmployee(idEmployee, employeeDTO);
    }

    @DeleteMapping("delete/{idEmployee}")
    public void deleteEmployee(@Valid @PathVariable @Min(value = 1, message = "The ID must be greater than zero.") long idEmployee) {
        _serviceEmployee.deleteEmployee(idEmployee);
    }

    @PostMapping("transfer/{idEmployeeDeposit}/{idEmployeeReceive}/{quantity}")
    public ResponseEntity<Map<String, Object>> transferSalary( @PathVariable long idEmployeeDeposit,  @PathVariable  long idEmployeeReceive, @PathVariable double quantity) {
        return _serviceEmployee.transferSalary(idEmployeeDeposit, idEmployeeReceive, quantity);
    }

    @GetMapping("averageSalary")
    public ResponseEntity<List<EmployeeDTO>> getAboveAverageSalaryEmployees() {
        return _serviceEmployee.getAboveAverageSalaryEmployees();
    }
}
