SET SERVEROUTPUT ON;

-- Тест представлений
SELECT * FROM v_coffee_shops_info;
SELECT * FROM v_drinks_in_all_shops;
SELECT * FROM v_desserts_in_all_shops;
SELECT * FROM v_baristas_info;
SELECT * FROM v_waiters_info;

-- Тест процедур
EXEC sp_most_popular_drink;
EXEC sp_most_popular_dessert;
EXEC sp_top_3_drinks;
EXEC sp_top_3_desserts;
EXEC sp_barista_worked_all_shops;
EXEC sp_black_list;

-- Тест триггеров
DELETE FROM shop_drinks WHERE shop_drink_id = 1;
SELECT * FROM archived_drinks;

DELETE FROM shop_desserts WHERE shop_dessert_id = 1;
SELECT * FROM archived_desserts;

UPDATE employees SET shop_id = 3 WHERE employee_id = 1;
SELECT * FROM employee_transfers;

ROLLBACK;

-- Пример запросов для анализа
SELECT 
    cs.shop_name,
    COUNT(o.order_id) AS total_orders,
    SUM(o.total_amount) AS total_revenue,
    ROUND(AVG(o.total_amount), 2) AS avg_check
FROM coffee_shops cs
LEFT JOIN orders o ON cs.shop_id = o.shop_id
GROUP BY cs.shop_id, cs.shop_name
ORDER BY total_revenue DESC;

SELECT 
    dc.drink_name,
    SUM(od.quantity) AS total_sold,
    SUM(od.subtotal) AS total_revenue
FROM drinks_catalog dc
JOIN shop_drinks sd ON dc.drink_id = sd.drink_id
JOIN order_drinks od ON sd.shop_drink_id = od.shop_drink_id
GROUP BY dc.drink_name
ORDER BY total_sold DESC;