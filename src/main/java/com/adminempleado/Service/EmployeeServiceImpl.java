package com.adminempleado.Service;

import com.adminempleado.DTO.EmployeeDTO;
import com.adminempleado.Entity.Employee;
import com.adminempleado.Repository.EmployeeDAO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeDAO _repositoryDAO;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        try {

            List<Employee> employees = _repositoryDAO.findAll();
            if (employees.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            List<EmployeeDTO> employeesDTO = employees.stream().map(employee -> new EmployeeDTO(employee.getID(), employee.getName(), employee.getPosition(), employee.getSalary())).collect(Collectors.toList());
            return ResponseEntity.ok(employeesDTO);
        } catch (Exception e) {
            log.error("Error getting all employees :" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public void postEmployee(EmployeeDTO employeeDTO) {
        try {
            Employee employee = mapper.map(employeeDTO, Employee.class);
            _repositoryDAO.save(employee);
        } catch (Exception e) {
            log.error("Error creating Employee :" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void deleteEmployee(long idEmployee) {
        try {
            _repositoryDAO.deleteById(idEmployee);
        } catch (Exception e) {
            log.error("Error deleting Employee :" + e);
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional
    public ResponseEntity<EmployeeDTO> updateEmployee(long idEmployee, EmployeeDTO employeeDTO) {
        try {
            EmployeeDTO _employeeDTO;
            Optional<Employee> _employee = _repositoryDAO.findById(idEmployee);
            if (_employee.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Employee newEmployee = _employee.get();
            newEmployee.setName(employeeDTO.getName());
            newEmployee.setPosition(employeeDTO.getPosition());
            newEmployee.setSalary(employeeDTO.getSalary());
            Employee updateEmployee = _repositoryDAO.save(newEmployee);
            _employeeDTO = mapper.map(updateEmployee, EmployeeDTO.class);
            return ResponseEntity.ok(_employeeDTO);
        } catch (Exception e) {
            log.error("Error updating Employee :" + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> transferSalary(long idEmployeeDeposit, long idEmployeeReceive, double quantity) {
        Map<String, Object> response = new HashMap<>();
        try {
            _repositoryDAO.transferSalary(idEmployeeDeposit, idEmployeeReceive, quantity);
            response.put("Success", true);
            response.put("Message", "Transfer completed successfully");
            response.put("Fech Today", LocalDateTime.now());
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            log.error("Error transferring balance to Employee :" + e);
            response.put("Success", false);
            response.put("Message", "Error internal" + e.getMessage());
            response.put("Fech Today", LocalDateTime.now());
            return ResponseEntity.ok(response);
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> getAboveAverageSalaryEmployees() {
        try {
            List<Employee> employees = _repositoryDAO.averageSalaryEmployees();
            if (employees.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            List<EmployeeDTO> employeesDTO = employees
                    .stream()
                    .map(employee -> new EmployeeDTO(
                            employee.getID(),
                            employee.getName(),
                            employee.getPosition(),
                            employee.getSalary()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(employeesDTO);
        } catch (Exception e) {
            log.error("Error searching for average salary Employees :" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
