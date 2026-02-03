@echo off
cd /d "C:\Users\Дом\Desktop\coffee-shop-db\coffee-shop-java"
cls

echo ============================================
echo    ИСПРАВЛЕНИЕ И КОМПИЛЯЦИЯ
echo ============================================

echo Шаг 1: Удаляем старые class файлы...
del classes\com\coffeeshop\models\Customer.class 2>nul
del classes\com\coffeeshop\dao\DiscountDAO.class 2>nul
del classes\com\coffeeshop\service\ReportService.class 2>nul
del classes\com\coffeeshop\ui\ReportMenu.class 2>nul

echo Шаг 2: Компилируем исправленный Customer.java...
javac -cp "lib/h2.jar" -d classes src\main\java\com\coffeeshop\models\Customer.java

echo Шаг 3: Компилируем DiscountDAO...
javac -cp "lib/h2.jar;classes" -d classes src\main\java\com\coffeeshop\dao\DiscountDAO.java

echo Шаг 4: Компилируем ReportService...
javac -cp "lib/h2.jar;classes" -d classes src\main\java\com\coffeeshop\service\ReportService.java

echo Шаг 5: Компилируем ReportMenu...
javac -cp "lib/h2.jar;classes" -d classes src\main\java\com\coffeeshop\ui\ReportMenu.java

echo Шаг 6: Компилируем остальные UI компоненты...
javac -cp "lib/h2.jar;classes" -d classes src\main\java\com\coffeeshop\ui\*.java

echo ============================================
echo Все исправления применены!
pause