CREATE OR REPLACE PROCEDURE sp_most_popular_drink AS
    v_drink_name drinks_catalog.drink_name%TYPE;
    v_total_quantity NUMBER;
    v_total_revenue NUMBER;
BEGIN
    SELECT 
        dc.drink_name,
        SUM(od.quantity) AS total_quantity,
        SUM(od.subtotal) AS total_revenue
    INTO v_drink_name, v_total_quantity, v_total_revenue
    FROM drinks_catalog dc
    JOIN shop_drinks sd ON dc.drink_id = sd.drink_id
    JOIN order_drinks od ON sd.shop_drink_id = od.shop_drink_id
    GROUP BY dc.drink_name
    ORDER BY total_quantity DESC
    FETCH FIRST 1 ROWS ONLY;
    
    DBMS_OUTPUT.PUT_LINE('Самый популярный напиток: ' || v_drink_name);
    DBMS_OUTPUT.PUT_LINE('Количество проданных порций: ' || v_total_quantity);
    DBMS_OUTPUT.PUT_LINE('Общая выручка: ' || v_total_revenue || ' руб.');
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Нет данных о продажах напитков');
END;
/

CREATE OR REPLACE PROCEDURE sp_most_popular_dessert AS
    v_dessert_name desserts_catalog.dessert_name%TYPE;
    v_total_quantity NUMBER;
    v_total_revenue NUMBER;
BEGIN
    SELECT 
        dsc.dessert_name,
        SUM(od.quantity) AS total_quantity,
        SUM(od.subtotal) AS total_revenue
    INTO v_dessert_name, v_total_quantity, v_total_revenue
    FROM desserts_catalog dsc
    JOIN shop_desserts sd ON dsc.dessert_id = sd.dessert_id
    JOIN order_desserts od ON sd.shop_dessert_id = od.shop_dessert_id
    GROUP BY dsc.dessert_name
    ORDER BY total_quantity DESC
    FETCH FIRST 1 ROWS ONLY;
    
    DBMS_OUTPUT.PUT_LINE('Самый популярный десерт: ' || v_dessert_name);
    DBMS_OUTPUT.PUT_LINE('Количество проданных порций: ' || v_total_quantity);
    DBMS_OUTPUT.PUT_LINE('Общая выручка: ' || v_total_revenue || ' руб.');
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Нет данных о продажах десертов');
END;
/

CREATE OR REPLACE PROCEDURE sp_top_3_drinks AS
BEGIN
    DBMS_OUTPUT.PUT_LINE('ТОП-3 напитков по количеству продаж:');
    
    FOR rec IN (
        SELECT 
            ROWNUM AS rank,
            drink_name,
            total_quantity,
            total_revenue
        FROM (
            SELECT 
                dc.drink_name,
                SUM(od.quantity) AS total_quantity,
                SUM(od.subtotal) AS total_revenue
            FROM drinks_catalog dc
            JOIN shop_drinks sd ON dc.drink_id = sd.drink_id
            JOIN order_drinks od ON sd.shop_drink_id = od.shop_drink_id
            GROUP BY dc.drink_name
            ORDER BY total_quantity DESC
        )
        WHERE ROWNUM <= 3
    ) LOOP
        DBMS_OUTPUT.PUT_LINE(rec.rank || '. ' || rec.drink_name || 
                           ' - ' || rec.total_quantity || ' порций, ' || 
                           rec.total_revenue || ' руб.');
    END LOOP;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Нет данных о продажах напитков');
END;
/

CREATE OR REPLACE PROCEDURE sp_top_3_desserts AS
BEGIN
    DBMS_OUTPUT.PUT_LINE('ТОП-3 десертов по количеству продаж:');
    
    FOR rec IN (
        SELECT 
            ROWNUM AS rank,
            dessert_name,
            total_quantity,
            total_revenue
        FROM (
            SELECT 
                dsc.dessert_name,
                SUM(od.quantity) AS total_quantity,
                SUM(od.subtotal) AS total_revenue
            FROM desserts_catalog dsc
            JOIN shop_desserts sd ON dsc.dessert_id = sd.dessert_id
            JOIN order_desserts od ON sd.shop_dessert_id = od.shop_dessert_id
            GROUP BY dsc.dessert_name
            ORDER BY total_quantity DESC
        )
        WHERE ROWNUM <= 3
    ) LOOP
        DBMS_OUTPUT.PUT_LINE(rec.rank || '. ' || rec.dessert_name || 
                           ' - ' || rec.total_quantity || ' порций, ' || 
                           rec.total_revenue || ' руб.');
    END LOOP;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Нет данных о продажах десертов');
END;
/

CREATE OR REPLACE PROCEDURE sp_barista_worked_all_shops AS
    v_employee_id employees.employee_id%TYPE;
    v_full_name VARCHAR2(100);
    v_shops_worked NUMBER;
    v_total_shops NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_total_shops FROM coffee_shops;
    
    SELECT 
        e.employee_id,
        e.first_name || ' ' || e.last_name AS full_name,
        COUNT(DISTINCT et.old_shop_id) + 1 AS shops_worked
    INTO v_employee_id, v_full_name, v_shops_worked
    FROM employees e
    LEFT JOIN employee_transfers et ON e.employee_id = et.employee_id
    WHERE e.position = 'бариста'
    GROUP BY e.employee_id, e.first_name, e.last_name
    HAVING COUNT(DISTINCT et.old_shop_id) + 1 >= v_total_shops
    FETCH FIRST 1 ROWS ONLY;
    
    IF v_employee_id IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('Бариста, работавший во всех кофейнях:');
        DBMS_OUTPUT.PUT_LINE('Имя: ' || v_full_name);
        DBMS_OUTPUT.PUT_LINE('Количество кофеен, в которых работал: ' || v_shops_worked);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Нет бариста, который работал бы во всех кофейнях');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Нет бариста, который работал бы во всех кофейнях');
END;
/