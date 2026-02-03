BEGIN
    FOR tab IN (SELECT table_name FROM user_tables) LOOP
        EXECUTE IMMEDIATE 'DELETE FROM ' || tab.table_name;
    END LOOP;
    COMMIT;
END;
/

-- 1. КОФЕЙНИ
INSERT INTO coffee_shops (shop_id, shop_name, address, phone, opening_date) VALUES 
(1, 'Кофейня у Марины', 'ул. Пушкина, 10', '+79991234567', '2018-05-15');
INSERT INTO coffee_shops (shop_id, shop_name, address, phone, opening_date) VALUES 
(2, 'Urban Coffee', 'пр. Ленина, 25', '+79997654321', '2020-03-10');
INSERT INTO coffee_shops (shop_id, shop_name, address, phone, opening_date) VALUES 
(3, 'Кофе & Книги', 'ул. Гагарина, 7', '+79995551234', '2022-11-20');

-- 2. СОТРУДНИКИ
INSERT INTO employees (employee_id, first_name, last_name, position, hire_date, salary, shop_id, email) VALUES
(1, 'Анна', 'Иванова', 'Менеджер', '2023-01-15', 50000, 1, 'anna@coffee.ru');
INSERT INTO employees (employee_id, first_name, last_name, position, hire_date, salary, shop_id, email) VALUES
(2, 'Петр', 'Сидоров', 'Бариста', '2023-03-10', 35000, 1, 'petr@coffee.ru');
INSERT INTO employees (employee_id, first_name, last_name, position, hire_date, salary, shop_id, email) VALUES
(3, 'Мария', 'Петрова', 'Официант', '2023-02-20', 30000, 1, 'maria@coffee.ru');
INSERT INTO employees (employee_id, first_name, last_name, position, hire_date, salary, shop_id, email) VALUES
(4, 'Иван', 'Кузнецов', 'Менеджер', '2022-11-05', 55000, 2, 'ivan@coffee.ru');
INSERT INTO employees (employee_id, first_name, last_name, position, hire_date, salary, shop_id, email) VALUES
(5, 'Елена', 'Смирнова', 'Бариста', '2023-04-12', 36000, 2, 'elena@coffee.ru');

UPDATE coffee_shops SET manager_id = 1 WHERE shop_id = 1;
UPDATE coffee_shops SET manager_id = 4 WHERE shop_id = 2;

-- 3. КАТАЛОГ НАПИТКОВ
INSERT INTO drinks_catalog (drink_id, drink_name, description, category) VALUES
(1, 'Эспрессо', 'Классический крепкий кофе', 'Кофе');
INSERT INTO drinks_catalog (drink_id, drink_name, description, category) VALUES
(2, 'Капучино', 'Кофе с молочной пенкой', 'Кофе');
INSERT INTO drinks_catalog (drink_id, drink_name, description, category) VALUES
(3, 'Латте', 'Кофе с большим количеством молока', 'Кофе');
INSERT INTO drinks_catalog (drink_id, drink_name, description, category) VALUES
(4, 'Американо', 'Эспрессо с водой', 'Кофе');
INSERT INTO drinks_catalog (drink_id, drink_name, description, category) VALUES
(5, 'Чай черный', 'Классический черный чай', 'Чай');
INSERT INTO drinks_catalog (drink_id, drink_name, description, category) VALUES
(6, 'Какао', 'Горячий шоколадный напиток', 'Горячий шоколад');

-- 4. КАТАЛОГ ДЕСЕРТОВ
INSERT INTO desserts_catalog (dessert_id, dessert_name, description, category) VALUES
(1, 'Тирамису', 'Итальянский десерт с кофе', 'Пирожные');
INSERT INTO desserts_catalog (dessert_id, dessert_name, description, category) VALUES
(2, 'Чизкейк', 'Сырный торт', 'Пирожные');
INSERT INTO desserts_catalog (dessert_id, dessert_name, description, category) VALUES
(3, 'Маффин шоколадный', 'Шоколадная кекс', 'Выпечка');
INSERT INTO desserts_catalog (dessert_id, dessert_name, description, category) VALUES
(4, 'Круассан', 'Французская слоеная выпечка', 'Выпечка');
INSERT INTO desserts_catalog (dessert_id, dessert_name, description, category) VALUES
(5, 'Медовик', 'Торт с медовыми коржами', 'Торты');

-- 5. МЕНЮ КОФЕЙНИ (напитки)
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES
(1, 1, 1, 150, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES
(2, 1, 2, 200, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES
(3, 1, 3, 220, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES
(4, 2, 1, 160, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES
(5, 2, 4, 180, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES
(6, 2, 5, 120, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES
(7, 3, 2, 210, 'Y');
INSERT INTO shop_drinks (shop_drink_id, shop_id, drink_id, price, is_available) VALUES
(8, 3, 6, 170, 'Y');

-- 6. МЕНЮ КОФЕЙНИ (десерты)
INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) VALUES
(1, 1, 1, 250, 'Y');
INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) VALUES
(2, 1, 3, 150, 'Y');
INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) VALUES
(3, 2, 2, 280, 'Y');
INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) VALUES
(4, 2, 4, 120, 'Y');
INSERT INTO shop_desserts (shop_dessert_id, shop_id, dessert_id, price, is_available) VALUES
(5, 3, 5, 300, 'Y');