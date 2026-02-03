CREATE OR REPLACE VIEW v_coffee_shops_info AS
SELECT 
    cs.shop_id,
    cs.shop_name,
    cs.address,
    cs.phone,
    cs.opening_date,
    e.first_name || ' ' || e.last_name AS manager_name,
    (SELECT COUNT(*) FROM employees emp WHERE emp.shop_id = cs.shop_id) AS employee_count,
    (SELECT COUNT(*) FROM shop_drinks sd WHERE sd.shop_id = cs.shop_id AND sd.is_available = 'Y') AS drinks_variety,
    (SELECT COUNT(*) FROM shop_desserts sds WHERE sds.shop_id = cs.shop_id AND sds.is_available = 'Y') AS desserts_variety
FROM coffee_shops cs
LEFT JOIN employees e ON cs.manager_id = e.employee_id
ORDER BY cs.shop_id;

CREATE OR REPLACE VIEW v_drinks_in_all_shops AS
SELECT 
    dc.drink_id,
    dc.drink_name,
    dc.description,
    dc.category,
    (SELECT COUNT(DISTINCT sd.shop_id) FROM shop_drinks sd WHERE sd.drink_id = dc.drink_id AND sd.is_available = 'Y') AS available_in_shops,
    (SELECT COUNT(*) FROM coffee_shops) AS total_shops
FROM drinks_catalog dc
WHERE (SELECT COUNT(DISTINCT sd.shop_id) FROM shop_drinks sd WHERE sd.drink_id = dc.drink_id AND sd.is_available = 'Y') = 
      (SELECT COUNT(*) FROM coffee_shops);

CREATE OR REPLACE VIEW v_desserts_in_all_shops AS
SELECT 
    dsc.dessert_id,
    dsc.dessert_name,
    dsc.description,
    dsc.category,
    (SELECT COUNT(DISTINCT sds.shop_id) FROM shop_desserts sds WHERE sds.dessert_id = dsc.dessert_id AND sds.is_available = 'Y') AS available_in_shops,
    (SELECT COUNT(*) FROM coffee_shops) AS total_shops
FROM desserts_catalog dsc
WHERE (SELECT COUNT(DISTINCT sds.shop_id) FROM shop_desserts sds WHERE sds.dessert_id = dsc.dessert_id AND sds.is_available = 'Y') = 
      (SELECT COUNT(*) FROM coffee_shops);

CREATE OR REPLACE VIEW v_baristas_info AS
SELECT 
    e.employee_id,
    e.first_name,
    e.last_name,
    cs.shop_name,
    e.hire_date,
    e.salary,
    e.email,
    ROUND(MONTHS_BETWEEN(SYSDATE, e.hire_date), 0) AS months_worked,
    (SELECT COUNT(*) FROM orders o WHERE o.employee_id = e.employee_id) AS total_orders_served,
    NVL((SELECT SUM(o.total_amount) FROM orders o WHERE o.employee_id = e.employee_id), 0) AS total_revenue
FROM employees e
JOIN coffee_shops cs ON e.shop_id = cs.shop_id
WHERE e.position = 'бариста'
ORDER BY e.shop_id, e.last_name;

CREATE OR REPLACE VIEW v_waiters_info AS
SELECT 
    e.employee_id,
    e.first_name,
    e.last_name,
    cs.shop_id,
    cs.shop_name,
    e.hire_date,
    e.salary,
    e.email,
    (SELECT COUNT(*) FROM orders o WHERE o.employee_id = e.employee_id) AS total_orders,
    NVL((SELECT SUM(o.total_amount) FROM orders o WHERE o.employee_id = e.employee_id), 0) AS total_sales,
    ROUND(NVL((SELECT AVG(o.total_amount) FROM orders o WHERE o.employee_id = e.employee_id), 0), 2) AS avg_order_amount
FROM employees e
JOIN coffee_shops cs ON e.shop_id = cs.shop_id
WHERE e.position = 'официант'
ORDER BY cs.shop_id, total_sales DESC;