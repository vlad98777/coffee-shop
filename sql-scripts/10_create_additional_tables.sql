-- Таблица для клиентов (добавляем в начало init.sql)
CREATE TABLE customers (
    customer_id NUMBER PRIMARY KEY,
    first_name VARCHAR2(50) NOT NULL,
    last_name VARCHAR2(50) NOT NULL,
    email VARCHAR2(100),
    phone VARCHAR2(20) NOT NULL,
    address VARCHAR2(200),
    registration_date DATE DEFAULT SYSDATE
);

-- Таблица для расписания работы кофеен
CREATE TABLE shop_schedules (
    schedule_id NUMBER PRIMARY KEY,
    shop_id NUMBER NOT NULL,
    work_date DATE NOT NULL,
    day_of_week VARCHAR2(20) NOT NULL,
    opening_time TIMESTAMP NOT NULL,
    closing_time TIMESTAMP NOT NULL,
    notes VARCHAR2(500),
    CONSTRAINT fk_schedule_shop FOREIGN KEY (shop_id) 
        REFERENCES coffee_shops(shop_id)
);

-- customer_id в таблицу orders (если его нет)
ALTER TABLE orders ADD customer_id NUMBER;
ALTER TABLE orders ADD CONSTRAINT fk_order_customer 
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id);

-- последовательности для новых таблиц
CREATE SEQUENCE seq_customers START WITH 1000 INCREMENT BY 1;
CREATE SEQUENCE seq_schedules START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_drinks START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE seq_desserts START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE seq_orders START WITH 1000 INCREMENT BY 1;


INSERT INTO customers (customer_id, first_name, last_name, phone, email) VALUES
(1, 'Ирина', 'Волкова', '+79161234567', 'irina@mail.ru'),
(2, 'Алексей', 'Комаров', '+79162345678', 'alex@mail.ru'),
(3, 'Светлана', 'Орлова', '+79163456789', 'sveta@mail.ru');

-- тестовое расписание
INSERT INTO shop_schedules (schedule_id, shop_id, work_date, day_of_week, opening_time, closing_time) VALUES
(1, 1, DATE '2024-01-15', 'Понедельник', 
 TIMESTAMP '2024-01-15 08:00:00', TIMESTAMP '2024-01-15 22:00:00', 'Обычный день'),
(2, 1, DATE '2024-01-16', 'Вторник',
 TIMESTAMP '2024-01-16 08:00:00', TIMESTAMP '2024-01-16 22:00:00', 'Обычный день'),
(3, 2, DATE '2024-01-15', 'Понедельник',
 TIMESTAMP '2024-01-15 09:00:00', TIMESTAMP '2024-01-15 21:00:00', 'Сокращенный день');

COMMIT;