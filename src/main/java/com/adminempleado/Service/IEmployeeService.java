package com.adminempleado.Service;

import com.adminempleado.DTO.EmployeeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface IEmployeeService {
    ResponseEntity<List<EmployeeDTO>> getAllEmployees();

    void postEmployee(EmployeeDTO employeDTO);

    void deleteEmployee(long idEmployee);

    ResponseEntity<EmployeeDTO> updateEmployee(long idEmployee, EmployeeDTO employeeDTO);

    ResponseEntity<Map<String, Object>> transferSalary(long idEmployeeDeposit, long idEmployeeReceive, double quantity);

    ResponseEntity<List<EmployeeDTO>> getAboveAverageSalaryEmployees();
}
