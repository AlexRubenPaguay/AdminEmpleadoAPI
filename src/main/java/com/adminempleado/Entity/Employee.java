package com.adminempleado.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employees")
public class Employee {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long ID;
    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters.")
    @Column(length = 100, nullable = false)
    private String Name;
    @Size(min = 2, max = 100, message = "Position must be between 2 and 100 characters.")
    @Column(length = 100)
    private String Position;
    private Double Salary;
}
