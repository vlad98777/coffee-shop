SET SERVEROUTPUT ON;
BEGIN
  DBMS_OUTPUT.PUT_LINE('=== НАЧАЛО СОЗДАНИЯ БД КОФЕЙНИ ===');
END;
/

-- 1. Основные таблицы
CREATE TABLE coffee_shops (
    shop_id NUMBER PRIMARY KEY,
    shop_name VARCHAR2(100) NOT NULL,
    address VARCHAR2(200) NOT NULL,
    phone VARCHAR2(20) NOT NULL,
    opening_date DATE NOT NULL,
    manager_id NUMBER
);

CREATE TABLE employees (
    employee_id NUMBER PRIMARY KEY,
    first_name VARCHAR2(50) NOT NULL,
    last_name VARCHAR2(50) NOT NULL,
    position VARCHAR2(50) NOT NULL,
    hire_date DATE NOT NULL,
    salary NUMBER(10,2) NOT NULL,
    shop_id NUMBER NOT NULL,
    email VARCHAR2(100)
);

-- 2. Данные
INSERT INTO coffee_shops VALUES 
(1, 'Кофейня Центральная', 'ул. Ленина, 1', '+79990001111', DATE '2020-01-15', NULL);
INSERT INTO coffee_shops VALUES 
(2, 'Кофейня Набережная', 'ул. Набережная, 25', '+79990002222', DATE '2021-03-10', NULL);

INSERT INTO employees VALUES 
(1, 'Анна', 'Иванова', 'бариста', DATE '2020-02-01', 45000, 1, 'anna@coffee.ru');
INSERT INTO employees VALUES 
(2, 'Петр', 'Сидоров', 'официант', DATE '2020-03-15', 35000, 1, 'petr@coffee.ru');

UPDATE coffee_shops SET manager_id = 1 WHERE shop_id = 1;

COMMIT;

-- 3. Проверка
BEGIN
  DBMS_OUTPUT.PUT_LINE('=== РЕЗУЛЬТАТЫ ===');
  FOR r IN (SELECT shop_name, address FROM coffee_shops) LOOP
    DBMS_OUTPUT.PUT_LINE('Кофейня: ' || r.shop_name || ' - ' || r.address);
  END LOOP;
  
  FOR r IN (SELECT first_name || ' ' || last_name as name, position FROM employees) LOOP
    DBMS_OUTPUT.PUT_LINE('Сотрудник: ' || r.name || ' - ' || r.position);
  END LOOP;
  
  DBMS_OUTPUT.PUT_LINE('=== ГОТОВО! ===');
END;
/