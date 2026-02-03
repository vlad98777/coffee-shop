SET SERVEROUTPUT ON;
DBMS_OUTPUT.PUT_LINE('=== Начало создания БД кофейни ===');

@01_create_tables.sql
@02_insert_data.sql
@03_create_archive_tables.sql
@04_create_sequences.sql
@04b_create_real_triggers.sql
@05_create_views.sql
@06_create_procedures.sql
@07_create_employee_violations.sql
@09_test_orders.sql
@08_test_queries.sql

DBMS_OUTPUT.PUT_LINE('=== БД кофейни успешно создана ===');
COMMIT;