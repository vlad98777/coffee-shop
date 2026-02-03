CREATE TABLE employee_violations (
    violation_id NUMBER PRIMARY KEY,
    employee_id NUMBER,
    violation_type VARCHAR2(100),
    violation_date DATE DEFAULT SYSDATE,
    description VARCHAR2(500),
    penalty_amount NUMBER,
    resolved CHAR(1) DEFAULT 'N'
);

CREATE SEQUENCE seq_violations START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE PROCEDURE sp_black_list AS
BEGIN
    DBMS_OUTPUT.PUT_LINE('ЧЕРНЫЙ СПИСОК СОТРУДНИКОВ');
    
    FOR rec IN (
        SELECT 
            e.employee_id,
            e.first_name || ' ' || e.last_name AS full_name,
            cs.shop_name,
            e.position,
            COUNT(ev.violation_id) AS violation_count,
            SUM(NVL(ev.penalty_amount, 0)) AS total_penalty
        FROM employees e
        JOIN coffee_shops cs ON e.shop_id = cs.shop_id
        LEFT JOIN employee_violations ev ON e.employee_id = ev.employee_id AND ev.resolved = 'N'
        GROUP BY e.employee_id, e.first_name, e.last_name, cs.shop_name, e.position
        HAVING COUNT(ev.violation_id) > 0
        ORDER BY violation_count DESC, total_penalty DESC
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Сотрудник: ' || rec.full_name);
        DBMS_OUTPUT.PUT_LINE('Кофейня: ' || rec.shop_name || ', Должность: ' || rec.position);
        DBMS_OUTPUT.PUT_LINE('Нарушений: ' || rec.violation_count || ', Штрафов: ' || rec.total_penalty || ' руб.');
        DBMS_OUTPUT.PUT_LINE('---');
    END LOOP;
    
    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Черный список пуст. Нарушений не обнаружено.');
    END IF;
END;
/