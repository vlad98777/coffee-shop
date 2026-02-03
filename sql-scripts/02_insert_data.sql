-- Кофейни
INSERT INTO coffee_shops (shop_id, shop_name, address, phone, opening_date) VALUES 
(1, 'Кофейня Центральная', 'ул. Ленина, 1', '+79990001111', DATE '2020-01-15');
INSERT INTO coffee_shops (shop_id, shop_name, address, phone, opening_date) VALUES 
(2, 'Кофейня Набережная', 'ул. Набережная, 25', '+79990002222', DATE '2021-03-10');
INSERT INTO coffee_shops (shop_id, shop_name, address, phone, opening_date) VALUES 
(3, 'Кофейня Парковая', 'ул. Парковая, 10', '+79990003333', DATE '2022-05-20');

-- Сотрудники
INSERT INTO employees (employee_id, first_name, last_name, position, hire_date, salary, shop_id, email) VALUES 
(1, 'Анна', 'Иванова', 'бариста', DATE '2020-02-01', 45000, 1, 'anna@coffee.ru');
INSERT INTO employees (employee_id, first_name, last_name, position, hire_date, salary, shop_id, email) VALUES 
(2, 'Петр', 'Сидоров', 'официант', DATE '2020-03-15', 35000, 1, 'petr@coffee.ru');
INSERT INTO employees (employee_id, first_name, last_name, position, hire_date, salary, shop_id, email) VALUES 
(3, 'Мария', 'Петрова', 'бариста', DATE '2021-04-01', 46000, 2, 'maria@coffee.ru');
INSERT INTO employees (employee_id, first_name, last_name, position, hire_date, salary, shop_id, email) VALUES 
(4, 'Иван', 'Кузнецов', 'менеджер', DATE '2021-03-01', 60000, 2, 'ivan@coffee.ru');
INSERT INTO employees (employee_id, first_name, last_name, position, hire_date, salary, shop_id, email) VALUES 
(5, 'Ольга', 'Смирнова', 'бариста', DATE '2022-06-01', 47000, 3, 'olga@coffee.ru');

-- Обновляем менеджера
UPDATE coffee_shops SET manager_id = 4 WHERE shop_id = 2;

-- Каталог напитков
INSERT INTO drinks_catalog (drink_id, drink_name, description, category) VALUES 
(1, 'Эспрессо', 'Классический крепкий кофе', 'кофе');
INSERT INTO drinks_catalog (drink_id, drink_name, description, category) VALUES 
(2, 'Капучино', 'Кофе с молочной пенкой', 'кофе');
INSERT INTO drinks_catalog (drink_id, drink_name, description, category) VALUES 
(3, 'Латте', 'Кофе с большим количеством молока', 'кофе');
INSERT INTO drinks_catalog (drink_id, drink_name, description, category) VALUES 
(4, 'Американо', 'Эспрессо с водой', 'кофе');
INSERT INTO drinks_catalog (drink_id, drink_name, description, category) VALUES 
(5, 'Зеленый чай', 'Традиционный зеленый чай', 'чай');

-- Каталог десертов
INSERT INTO desserts_catalog (dessert_id, dessert_name, description, category) VALUES 
(1, 'Тирамису', 'Итальянский десерт', 'торт');
INSERT INTO desserts_catalog (dessert_id, dessert_name, description, category) VALUES 
(2, 'Чизкейк', 'Сливочный сырный торт', 'торт');
INSERT INTO desserts_catalog (dessert_id, dessert_name, description, category) VALUES 
(3, 'Маффин шоколадный', 'Шоколадный кекс', 'печенье');
INSERT INTO desserts_catalog (dessert_id, dessert_name, description, category) VALUES 
(4, 'Эклер', 'Заварное пирожное', 'пирожное');
INSERT INTO desserts_catalog (dessert_id, dessert_name, description, category) VALUES 
(5, 'Круассан', 'Французская выпечка', 'печенье');

-- Напитки в кофейнях
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES (1, 1, 1, 150, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES (2, 1, 2, 200, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES (3, 1, 3, 220, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES (4, 1, 4, 170, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES (5, 1, 5, 100, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES (6, 2, 1, 160, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES (7, 2, 2, 210, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES (8, 2, 3, 230, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES (9, 2, 5, 110, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES (10, 3, 1, 155, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES (11, 3, 2, 205, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES (12, 3, 3, 225, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES (13, 3, 4, 175, 'Y');

-- Десерты в кофейнях
INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) VALUES (1, 1, 1, 250, 'Y');
INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) VALUES (2, 1, 2, 230, 'Y');
INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) VALUES (3, 1, 3, 150, 'Y');
INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) VALUES (4, 2, 1, 260, 'Y');
INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) VALUES (5, 2, 2, 240, 'Y');
INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) VALUES (6, 2, 4, 180, 'Y');
INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) VALUES (7, 3, 2, 235, 'Y');
INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) VALUES (8, 3, 3, 155, 'Y');
INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) VALUES (9, 3, 5, 120, 'Y');

COMMIT;