

-- 1. КОФЕЙНИ 
CREATE TABLE IF NOT EXISTS coffee_shops (
    shop_id INT PRIMARY KEY,
    shop_name VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    opening_date DATE NOT NULL,
    manager_id INT
);

-- 2. СОТРУДНИКИ 
CREATE TABLE IF NOT EXISTS employees (
    employee_id INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    position VARCHAR(50) NOT NULL,
    hire_date DATE NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    shop_id INT NOT NULL,
    email VARCHAR(100),
    CONSTRAINT chk_salary CHECK (salary > 0)
);

-- 3. КАТАЛОГ НАПИТКОВ 
CREATE TABLE IF NOT EXISTS drinks_catalog (
    drink_id INT PRIMARY KEY,
    drink_name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    category VARCHAR(50) NOT NULL
);

-- 4. КАТАЛОГ ДЕСЕРТОВ 
CREATE TABLE IF NOT EXISTS desserts_catalog (
    dessert_id INT PRIMARY KEY,
    dessert_name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    category VARCHAR(50) NOT NULL
);

-- 5. МЕНЮ КОФЕЙНИ (напитки) 
CREATE TABLE IF NOT EXISTS shop_drinks (
    shop_drink_id INT PRIMARY KEY,
    shop_id INT NOT NULL,
    drink_id INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    is_available CHAR(1) DEFAULT 'Y',
    CONSTRAINT chk_drink_price CHECK (price > 0)
);

-- 6. МЕНЮ КОФЕЙНИ (десерты) 
CREATE TABLE IF NOT EXISTS shop_desserts (
    shop_dessert_id INT PRIMARY KEY,
    shop_id INT NOT NULL,
    dessert_id INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    is_available CHAR(1) DEFAULT 'Y',
    CONSTRAINT chk_dessert_price CHECK (price > 0)
);

-- 7. ЗАКАЗЫ 
CREATE TABLE IF NOT EXISTS orders (
    order_id INT PRIMARY KEY,
    shop_id INT NOT NULL,
    employee_id INT NOT NULL,
    order_date TIMESTAMP NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) DEFAULT 'Новый',
    CONSTRAINT chk_total_amount CHECK (total_amount >= 0)
);

-- 8. СОСТАВ ЗАКАЗОВ (напитки) 
CREATE TABLE IF NOT EXISTS order_drinks (
    order_drink_id INT PRIMARY KEY,
    order_id INT NOT NULL,
    shop_drink_id INT NOT NULL,
    quantity INT NOT NULL,
    price_per_unit DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2),
    CONSTRAINT chk_quantity_drink CHECK (quantity > 0)
);

-- 9. СОСТАВ ЗАКАЗОВ (десерты) 
CREATE TABLE IF NOT EXISTS order_desserts (
    order_dessert_id INT PRIMARY KEY,
    order_id INT NOT NULL,
    shop_dessert_id INT NOT NULL,
    quantity INT NOT NULL,
    price_per_unit DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2),
    CONSTRAINT chk_quantity_dessert CHECK (quantity > 0)
);