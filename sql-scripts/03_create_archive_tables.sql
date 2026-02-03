CREATE TABLE archived_drinks (
    archive_id NUMBER PRIMARY KEY,
    original_drink_id NUMBER,
    drink_name VARCHAR2(100),
    description VARCHAR2(500),
    category VARCHAR2(50),
    shop_id NUMBER,
    price NUMBER(10,2),
    deletion_date DATE DEFAULT SYSDATE,
    deleted_by VARCHAR2(100) DEFAULT USER
);

CREATE TABLE archived_desserts (
    archive_id NUMBER PRIMARY KEY,
    original_dessert_id NUMBER,
    dessert_name VARCHAR2(100),
    description VARCHAR2(500),
    category VARCHAR2(50),
    shop_id NUMBER,
    price NUMBER(10,2),
    deletion_date DATE DEFAULT SYSDATE,
    deleted_by VARCHAR2(100) DEFAULT USER
);

CREATE TABLE employee_transfers (
    transfer_id NUMBER PRIMARY KEY,
    employee_id NUMBER,
    first_name VARCHAR2(50),
    last_name VARCHAR2(50),
    position VARCHAR2(50),
    old_shop_id NUMBER,
    new_shop_id NUMBER,
    transfer_date DATE DEFAULT SYSDATE,
    transferred_by VARCHAR2(100) DEFAULT USER
);