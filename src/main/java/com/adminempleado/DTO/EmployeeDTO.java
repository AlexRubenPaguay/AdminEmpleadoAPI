package com.adminempleado.DTO;

import com.adminempleado.Entity.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
    private long ID;
    private String Name;
    private String Position;
    private Double Salary;

    public EmployeeDTO(Employee employee) {
        this.ID = employee.getID();
        this.Name = employee.getName();
        this.Position = employee.getPosition();
        this.Salary = employee.getSalary();
    }
}
