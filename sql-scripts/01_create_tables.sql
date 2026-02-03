-- Основные таблицы
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
    email VARCHAR2(100),
    CONSTRAINT chk_salary CHECK (salary > 0)
);

CREATE TABLE drinks_catalog (
    drink_id NUMBER PRIMARY KEY,
    drink_name VARCHAR2(100) NOT NULL,
    description VARCHAR2(500),
    category VARCHAR2(50) NOT NULL
);

CREATE TABLE desserts_catalog (
    dessert_id NUMBER PRIMARY KEY,
    dessert_name VARCHAR2(100) NOT NULL,
    description VARCHAR2(500),
    category VARCHAR2(50) NOT NULL
);

CREATE TABLE shop_drinks (
    shop_drink_id NUMBER PRIMARY KEY,
    shop_id NUMBER NOT NULL,
    drink_id NUMBER NOT NULL,
    price NUMBER(10,2) NOT NULL,
    is_available CHAR(1) DEFAULT 'Y' CHECK (is_available IN ('Y', 'N')),
    CONSTRAINT chk_drink_price CHECK (price > 0)
);

CREATE TABLE shop_desserts (
    shop_dessert_id NUMBER PRIMARY KEY,
    shop_id NUMBER NOT NULL,
    dessert_id NUMBER NOT NULL,
    price NUMBER(10,2) NOT NULL,
    is_available CHAR(1) DEFAULT 'Y' CHECK (is_available IN ('Y', 'N')),
    CONSTRAINT chk_dessert_price CHECK (price > 0)
);

CREATE TABLE orders (
    order_id NUMBER PRIMARY KEY,
    shop_id NUMBER NOT NULL,
    employee_id NUMBER NOT NULL,
    order_date TIMESTAMP NOT NULL,
    total_amount NUMBER(10,2) NOT NULL,
    status VARCHAR2(50) DEFAULT 'Новый' CHECK (status IN ('Новый', 'В процессе', 'Завершен', 'Отменен')),
    CONSTRAINT chk_total_amount CHECK (total_amount >= 0)
);

CREATE TABLE order_drinks (
    order_drink_id NUMBER PRIMARY KEY,
    order_id NUMBER NOT NULL,
    shop_drink_id NUMBER NOT NULL,
    quantity NUMBER NOT NULL CHECK (quantity > 0),
    price_per_unit NUMBER(10,2) NOT NULL,
    subtotal NUMBER(10,2)
);

CREATE TABLE order_desserts (
    order_dessert_id NUMBER PRIMARY KEY,
    order_id NUMBER NOT NULL,
    shop_dessert_id NUMBER NOT NULL,
    quantity NUMBER NOT NULL CHECK (quantity > 0),
    price_per_unit NUMBER(10,2) NOT NULL,
    subtotal NUMBER(10,2)
);

-- Добавляем внешние ключи
ALTER TABLE coffee_shops ADD CONSTRAINT fk_manager 
    FOREIGN KEY (manager_id) REFERENCES employees(employee_id);

ALTER TABLE employees ADD CONSTRAINT fk_employee_shop 
    FOREIGN KEY (shop_id) REFERENCES coffee_shops(shop_id);

ALTER TABLE shop_drinks ADD CONSTRAINT fk_shop_drinks_shop 
    FOREIGN KEY (shop_id) REFERENCES coffee_shops(shop_id);
    
ALTER TABLE shop_drinks ADD CONSTRAINT fk_shop_drinks_drink 
    FOREIGN KEY (drink_id) REFERENCES drinks_catalog(drink_id);

ALTER TABLE shop_desserts ADD CONSTRAINT fk_shop_desserts_shop 
    FOREIGN KEY (shop_id) REFERENCES coffee_shops(shop_id);
    
ALTER TABLE shop_desserts ADD CONSTRAINT fk_shop_desserts_dessert 
    FOREIGN KEY (dessert_id) REFERENCES desserts_catalog(dessert_id);

ALTER TABLE orders ADD CONSTRAINT fk_orders_shop 
    FOREIGN KEY (shop_id) REFERENCES coffee_shops(shop_id);
    
ALTER TABLE orders ADD CONSTRAINT fk_orders_employee 
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id);

ALTER TABLE order_drinks ADD CONSTRAINT fk_order_drinks_order 
    FOREIGN KEY (order_id) REFERENCES orders(order_id);
    
ALTER TABLE order_drinks ADD CONSTRAINT fk_order_drinks_shop_drink 
    FOREIGN KEY (shop_drink_id) REFERENCES shop_drinks(shop_drink_id);

ALTER TABLE order_desserts ADD CONSTRAINT fk_order_desserts_order 
    FOREIGN KEY (order_id) REFERENCES orders(order_id);
    
ALTER TABLE order_desserts ADD CONSTRAINT fk_order_desserts_shop_dessert 
    FOREIGN KEY (shop_dessert_id) REFERENCES shop_desserts(shop_dessert_id);