@echo off
cd /d "C:\Users\Дом\Desktop\coffee-shop-db\coffee-shop-java"
cls

echo ============================================
echo    ПОЛНАЯ КОМПИЛЯЦИЯ ПРОЕКТА
echo ============================================

echo 1. Очищаем папку classes...
if exist classes rmdir /s /q classes
mkdir classes
mkdir classes\com 2>nul
mkdir classes\com\coffeeshop 2>nul
mkdir classes\com\coffeeshop\ui 2>nul
mkdir classes\com\coffeeshop\dao 2>nul
mkdir classes\com\coffeeshop\models 2>nul
mkdir classes\com\coffeeshop\service 2>nul
mkdir classes\com\coffeeshop\util 2>nul
mkdir classes\com\coffeeshop\utils 2>nul

echo 2. Компилируем DatabaseConnection (основа)...
javac -cp "lib/h2.jar" -d classes src\main\java\com\coffeeshop\DatabaseConnection.java

echo 3. Компилируем модели...
javac -cp "lib/h2.jar;classes" -d classes src\main\java\com\coffeeshop\models\*.java

echo 4. Компилируем DAO...
javac -cp "lib/h2.jar;classes" -d classes src\main\java\com\coffeeshop\dao\*.java

echo 5. Компилируем сервисы...
javac -cp "lib/h2.jar;classes" -d classes src\main\java\com\coffeeshop\service\*.java

echo 6. Компилируем утилиты...
javac -cp "lib/h2.jar;classes" -d classes src\main\java\com\coffeeshop\utils\*.java
javac -cp "lib/h2.jar;classes" -d classes src\main\java\com\coffeeshop\util\*.java

echo 7. Компилируем UI...
javac -cp "lib/h2.jar;classes" -d classes src\main\java\com\coffeeshop\ui\*.java

echo ============================================
echo КОМПИЛЯЦИЯ ЗАВЕРШЕНА!
echo.
echo Скомпилированные классы:
dir /s classes | find ".class" | find /c /v ""
echo.
pause