# Дипломный проект по курсу «Инженер по тестированию»

Краткое описание проекта :
Приложение — это веб-сервис, который предлагает купить тур по определённой цене двумя способами:

Обычная оплата по дебетовой карте.
Уникальная технология: выдача кредита по данным банковской карты.
Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:

сервису платежей, далее Payment Gate;
кредитному сервису, далее Credit Gate.

База данных хранит информацию о заказах, платежах, статусах карт, способах оплаты.

Начало работы
1. Клонировать репозиторий
2. Если не установлен Docker Desktop, необходимо его и установить.
3. Запустить Docker Desktop.
4. Открыть проект в IntelliJ IDEA
   
## Prerequisites
* IntelliJ IDEA
* Docker Desktop
* Java jdk-11.0.19.7
* Google Chrome версия 118.0.5993.89

## Установка и запуск.

1. Запустить контейнеры командой в терминале `docker-compose up`

2. Открыть новую вкладку терминала через "+", ввести следующую команду в зависимости от базы данных :
- `java -jar artifacts/aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app
  ` - для MySQL
- `java -jar artifacts/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app` - для PostgreSQL
  
3. Приложение запускается по адресу http://localhost:8080/

Для запуска тестов :
4. Открыть новую вкладку терминала через "+", ввести команду в зависимости от запущенной БД в п.2 Запуска:
- `.\gradlew clean test -DdbUrl=jdbc:mysql://localhost:3306/app` - для MySQL
- `.\gradlew clean test -DdbUrl=jdbc:postgresql://localhost:5432/app` - для PostgreSQL

## Формирование отчета по результатам тестирования
1. Ввести в терминале команду `.\gradlew allureServe`
2. Для закрытия отчета комбинация `Ctrl + C`, чтобы подтвердить выход, необходимо ввести `Y`
3. Для остановки работы контейнеров ввести команду `docker-compose down`

## Документация
[План автоматизации тестирования](https://github.com/SmorodaKsu/Diplomaqa/blob/main/docs/Plan.md)
[Отчёт о проведённом тестировании](https://github.com/SmorodaKsu/Diplomaqa/blob/main/docs/Report.md)
[Отчёт о проведённой автоматизации](https://github.com/SmorodaKsu/Diplomaqa/blob/main/docs/Summary.md)
