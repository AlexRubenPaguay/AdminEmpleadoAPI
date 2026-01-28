DECLARE
    v_count NUMBER;
BEGIN

-- CARGANDO DATOS

    SELECT COUNT(*) INTO v_count FROM EMPLOYEES;
    
    IF v_count = 0 THEN
        INSERT INTO EMPLOYEES VALUES(1, 'Ricardo Loza', 'Analista de Sistemas', 3000.00);
        INSERT INTO EMPLOYEES VALUES(2, 'María García', 'Gerente de Proyecto', 4500.00);
        INSERT INTO EMPLOYEES VALUES(3, 'Carlos López', 'Analista de Sistemas', 3000.00);
        INSERT INTO EMPLOYEES VALUES(4, 'Ana Rodríguez', 'Desarrollador Junior', 2000.00);
        INSERT INTO EMPLOYEES VALUES(5, 'Pedro Sánchez', 'Administrador de BD', 3500.00); 
		INSERT INTO EMPLOYEES VALUES(6, 'Bill Gates', 'GERENTE TI/CEO', 5800.00); 
		INSERT INTO EMPLOYEES VALUES(7, 'Marck Zuckerberg', 'Desarrollador Senior', 5000.00);	
		INSERT INTO EMPLOYEES VALUES(8, 'Viviana Smith', 'Desarrollador Trainee', 1500.00);		
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Datos cargados: ' || SQL%ROWCOUNT || ' registros');
    ELSE
        DBMS_OUTPUT.PUT_LINE('La tabla ya tiene ' || v_count || ' registros');
    END IF;
END;
/


-- CREANDO LA VISTA

	CREATE OR REPLACE VIEW vw_average_employees_salary 
AS

WITH avg_salary AS(
SELECT NVL(AVG(salary),0) AS  salary_avg FROM employees
)

select id,name,position,salary
from employees e CROSS JOIN avg_salary s
where e.salary > s.salary_avg
order by e.salary DESC;
/

-- CREANDO PROCEDIMIENTO ALMACENADO

CREATE OR REPLACE PROCEDURE sp_trasfer_salary (
    p_id_employee_origin  IN employees.id%TYPE,
    p_id_employee_destiny IN employees.id%TYPE,
    p_quantity            IN employees.salary%TYPE
) AS

    v_salary_origin  employees.salary%TYPE;
    v_salary_destiny employees.salary%TYPE;
    v_contador       employees.id%TYPE;
BEGIN
    IF p_quantity <= 0 THEN
        raise_application_error(
                               -20001,
                               'The quantity must be greater than zero'
        );
    END IF;
    IF p_id_employee_origin = p_id_employee_destiny THEN
        raise_application_error(
                               -20002,
                               'Cannot transfer to the same employee'
        );
    END IF;
    SELECT
        COUNT(*)
    INTO v_contador
    FROM
        employees
    WHERE
        id = p_id_employee_origin;

    IF v_contador = 0 THEN
        raise_application_error(
                               -20003,
                               'Origin employee does not exist'
        );
    END IF;
    SELECT
        COUNT(*)
    INTO v_contador
    FROM
        employees
    WHERE
        id = p_id_employee_destiny;

    IF v_contador = 0 THEN
        raise_application_error(
                               -20004,
                               'Destination employee does not exist'
        );
    END IF;
    SELECT
        salary
    INTO v_salary_origin
    FROM
        employees
    WHERE
        id = p_id_employee_origin;

    IF v_salary_origin < p_quantity THEN
        raise_application_error(
                               -20005,
                               'Insufficient salary. Current salary: $' || v_salary_origin
        );
    END IF;

    SELECT
        salary
    INTO v_salary_destiny
    FROM
        employees
    WHERE
        id = p_id_employee_destiny;

    UPDATE employees
    SET
        salary = salary - p_quantity
    WHERE
        id = p_id_employee_origin;

    UPDATE employees
    SET
        salary = salary + p_quantity
    WHERE
        id = p_id_employee_destiny;

    COMMIT;
    dbms_output.put_line('Successful transfer');
    dbms_output.put_line('Employee origin ID :'
                         || TO_CHAR(p_id_employee_origin)
                         || ' - New Salary $ '
                         || TO_CHAR(v_salary_origin - p_quantity));
    dbms_output.put_line('Employee destiny ID :'
                         || TO_CHAR(p_id_employee_destiny)
                         || ' - New Salary $ '
                         || TO_CHAR(v_salary_destiny + p_quantity));
      
      EXCEPTION
           WHEN OTHERS THEN
             ROLLBACK;
             DBMS_OUTPUT.PUT_LINE('ERROR: ' ||SQLERRM);
             RAISE;
                         

END sp_trasfer_salary;
/

BEGIN
-- CREANDO EL INDICE A LA COLUMNA salary
DECLARE
 v_count NUMBER;
BEGIN
    -- Verificar si el índice existe
    SELECT COUNT(*)
    INTO v_count
    FROM user_indexes
    WHERE index_name = UPPER('IDX_EMPLOYEES_SALARY');
    
    IF v_count = 0 THEN
        EXECUTE IMMEDIATE 'CREATE INDEX idx_employees_salary ON employees(salary DESC)';
        DBMS_OUTPUT.PUT_LINE('Índice idx_employees_salary creado.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('El índice idx_employees_salary ya existe.');
    END IF;
END;
END;
/