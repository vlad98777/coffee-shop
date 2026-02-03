CREATE OR REPLACE TRIGGER trg_archive_drink
BEFORE DELETE ON shop_drinks
FOR EACH ROW
DECLARE
    v_drink_name drinks_catalog.drink_name%TYPE;
    v_description drinks_catalog.description%TYPE;
    v_category drinks_catalog.category%TYPE;
BEGIN
    SELECT drink_name, description, category 
    INTO v_drink_name, v_description, v_category
    FROM drinks_catalog 
    WHERE drink_id = :OLD.drink_id;
    
    INSERT INTO archived_drinks (
        archive_id, original_drink_id, drink_name, 
        description, category, shop_id, price
    ) VALUES (
        seq_archived_drinks.NEXTVAL, :OLD.drink_id, 
        v_drink_name, v_description, v_category, 
        :OLD.shop_id, :OLD.price
    );
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        NULL;
    WHEN OTHERS THEN
        NULL;
END;
/

CREATE OR REPLACE TRIGGER trg_archive_dessert
BEFORE DELETE ON shop_desserts
FOR EACH ROW
DECLARE
    v_dessert_name desserts_catalog.dessert_name%TYPE;
    v_description desserts_catalog.description%TYPE;
    v_category desserts_catalog.category%TYPE;
BEGIN
    SELECT dessert_name, description, category 
    INTO v_dessert_name, v_description, v_category
    FROM desserts_catalog 
    WHERE dessert_id = :OLD.dessert_id;
    
    INSERT INTO archived_desserts (
        archive_id, original_dessert_id, dessert_name, 
        description, category, shop_id, price
    ) VALUES (
        seq_archived_desserts.NEXTVAL, :OLD.dessert_id, 
        v_dessert_name, v_description, v_category, 
        :OLD.shop_id, :OLD.price
    );
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        NULL;
    WHEN OTHERS THEN
        NULL;
END;
/

CREATE OR REPLACE TRIGGER trg_archive_employee_transfer
BEFORE UPDATE OF shop_id ON employees
FOR EACH ROW
WHEN (OLD.shop_id IS NOT NULL AND NEW.shop_id IS NOT NULL AND OLD.shop_id != NEW.shop_id)
DECLARE
BEGIN
    INSERT INTO employee_transfers (
        transfer_id, employee_id, first_name, last_name, 
        position, old_shop_id, new_shop_id
    ) VALUES (
        seq_employee_transfers.NEXTVAL, :OLD.employee_id, 
        :OLD.first_name, :OLD.last_name, :OLD.position,
        :OLD.shop_id, :NEW.shop_id
    );
EXCEPTION
    WHEN OTHERS THEN
        NULL;
END;
/