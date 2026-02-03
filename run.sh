#!/bin/bash
# run.sh

# Компиляция проекта
echo "Компиляция проекта..."
find coffee-shop-java/src -name "*.java" > sources.txt
javac -cp "coffee-shop-java/lib/*" -d coffee-shop-java/classes @sources.txt

# Проверка успешности компиляции
if [ $? -eq 0 ]; then
    echo "Компиляция успешна!"
    
    # Создание JAR
    echo "Создание JAR файла..."
    jar cvfm coffee-shop-java/coffee-shop.jar coffee-shop-java/manifest.txt -C coffee-shop-java/classes .
    
    # Запуск приложения
    echo "Запуск приложения..."
    java -cp "coffee-shop-java/coffee-shop.jar:coffee-shop-java/lib/*" com.coffeeshop.Main
else
    echo "Ошибка компиляции!"
    exit 1
fi