--  дополнительные заказы для тестирования статистики
INSERT INTO orders (order_id, shop_id, employee_id, order_date, total_amount, status) VALUES 
(6, 1, 2, TIMESTAMP '2024-01-12 14:20:00', 750, 'Завершен');
INSERT INTO orders (order_id, shop_id, employee_id, order_date, total_amount, status) VALUES 
(7, 2, 3, TIMESTAMP '2024-01-12 15:30:00', 480, 'Завершен');
INSERT INTO orders (order_id, shop_id, employee_id, order_date, total_amount, status) VALUES 
(8, 2, 3, TIMESTAMP '2024-01-12 16:45:00', 320, 'Завершен');
INSERT INTO orders (order_id, shop_id, employee_id, order_date, total_amount, status) VALUES 
(9, 3, 5, TIMESTAMP '2024-01-13 10:15:00', 615, 'Завершен');
INSERT INTO orders (order_id, shop_id, employee_id, order_date, total_amount, status) VALUES 
(10, 3, 5, TIMESTAMP '2024-01-13 11:30:00', 410, 'Завершен');
INSERT INTO orders (order_id, shop_id, employee_id, order_date, total_amount, status) VALUES 
(11, 1, 2, TIMESTAMP '2024-01-13 12:45:00', 550, 'Завершен');
INSERT INTO orders (order_id, shop_id, employee_id, order_date, total_amount, status) VALUES 
(12, 2, 3, TIMESTAMP '2024-01-14 09:30:00', 840, 'Завершен');
INSERT INTO orders (order_id, shop_id, employee_id, order_date, total_amount, status) VALUES 
(13, 2, 3, TIMESTAMP '2024-01-14 14:20:00', 525, 'Завершен');
INSERT INTO orders (order_id, shop_id, employee_id, order_date, total_amount, status) VALUES 
(14, 3, 5, TIMESTAMP '2024-01-14 16:10:00', 380, 'Завершен');
INSERT INTO orders (order_id, shop_id, employee_id, order_date, total_amount, status) VALUES 
(15, 1, 2, TIMESTAMP '2024-01-15 10:45:00', 920, 'Завершен');

--  детали заказов (напитки)
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(8, 6, 2, 2, 200, 400);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(9, 6, 3, 1, 220, 220);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(10, 6, 5, 1, 100, 100);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(11, 7, 7, 2, 210, 420);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(12, 8, 8, 1, 230, 230);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(13, 8, 9, 1, 110, 110);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(14, 9, 11, 2, 205, 410);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(15, 9, 12, 1, 225, 225);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(16, 10, 11, 1, 205, 205);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(17, 10, 13, 1, 175, 175);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(18, 11, 2, 1, 200, 200);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(19, 11, 3, 1, 220, 220);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(20, 11, 4, 1, 170, 170);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(21, 12, 6, 3, 160, 480);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(22, 12, 7, 2, 210, 420);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(23, 13, 8, 1, 230, 230);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(24, 13, 9, 2, 110, 220);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(25, 13, 7, 1, 210, 210);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(26, 14, 10, 2, 155, 310);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(27, 15, 2, 3, 200, 600);
INSERT INTO order_drinks (order_drink_id, order_id, shop_drink_id, quantity, price_per_unit, subtotal) VALUES 
(28, 15, 3, 2, 220, 440);

--  детали заказов (десерты)
INSERT INTO order_desserts (order_dessert_id, order_id, shop_dessert_id, quantity, price_per_unit, subtotal) VALUES 
(5, 6, 1, 1, 250, 250);
INSERT INTO order_desserts (order_dessert_id, order_id, shop_dessert_id, quantity, price_per_unit, subtotal) VALUES 
(6, 6, 2, 1, 230, 230);
INSERT INTO order_desserts (order_dessert_id, order_id, shop_dessert_id, quantity, price_per_unit, subtotal) VALUES 
(7, 7, 4, 1, 260, 260);
INSERT INTO order_desserts (order_dessert_id, order_id, shop_dessert_id, quantity, price_per_unit, subtotal) VALUES 
(8, 8, 5, 1, 240, 240);
INSERT INTO order_desserts (order_dessert_id, order_id, shop_dessert_id, quantity, price_per_unit, subtotal) VALUES 
(9, 9, 7, 1, 235, 235);
INSERT INTO order_desserts (order_dessert_id, order_id, shop_dessert_id, quantity, price_per_unit, subtotal) VALUES 
(10, 9, 8, 1, 155, 155);
INSERT INTO order_desserts (order_dessert_id, order_id, shop_dessert_id, quantity, price_per_unit, subtotal) VALUES 
(11, 10, 7, 1, 235, 235);
INSERT INTO order_desserts (order_dessert_id, order_id, shop_dessert_id, quantity, price_per_unit, subtotal) VALUES 
(12, 11, 3, 2, 150, 300);
INSERT INTO order_desserts (order_dessert_id, order_id, shop_dessert_id, quantity, price_per_unit, subtotal) VALUES 
(13, 12, 4, 2, 260, 520);
INSERT INTO order_desserts (order_dessert_id, order_id, shop_dessert_id, quantity, price_per_unit, subtotal) VALUES 
(14, 13, 5, 1, 240, 240);
INSERT INTO order_desserts (order_dessert_id, order_id, shop_dessert_id, quantity, price_per_unit, subtotal) VALUES 
(15, 14, 9, 1, 120, 120);
INSERT INTO order_desserts (order_dessert_id, order_id, shop_dessert_id, quantity, price_per_unit, subtotal) VALUES 
(16, 15, 1, 1, 250, 250);
INSERT INTO order_desserts (order_dessert_id, order_id, shop_dessert_id, quantity, price_per_unit, subtotal) VALUES 
(17, 15, 3, 1, 150, 150);

COMMIT;