package com.adminempleado.Repository;

import com.adminempleado.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {
    @Procedure(procedureName = "sp_trasfer_salary")
    public void transferSalary(@Param("p_id_employee_origin") long idEmployeeDeposit, @Param("p_id_employee_destiny") long idEmployeeReceive, @Param("p_quantity") double quantity);

    @Query(value = "SELECT * FROM vw_average_employees_salary", nativeQuery = true)
    public List<Employee> averageSalaryEmployees();
}
