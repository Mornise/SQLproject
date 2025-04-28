package org.example;

import java.sql.*;
import java.util.Scanner;

import static org.example.Properties.*;
import static org.example.SQLQuery.*;


public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Меню опций:");
            System.out.println("1. Вывести все товары");
            System.out.println("2. Получить список всех клиентов");
            System.out.println("3. Найти товар с самой высокой ценой");
            System.out.println("4. Найти товары, которых на складе больше N штук");
            System.out.println("5. Найти клиентов, у которых имя начинается с буквы ?");
            System.out.println("6. Определить суммарную стоимость всех заказов");
            System.out.println("7. Найти товары с ценой от п до m");
            System.out.println("8. Посчитать общее количество клиентов");
            System.out.println("9. Определить среднюю цену товаров");
            System.out.println("10. Найти клиента, который сделал наибольшее количество заказов");
            System.out.println("11. Определить среднюю сумму заказа");
            System.out.println("12. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayAllProducts(connection);
                    break;
                case 2:
                    displayAllCustomers(connection);
                    break;
                case 3:
                    findMostExpensiveProduct(connection);
                    break;
                case 4:
                    findProductsInStockGreaterThanN(scanner, connection);
                    break;
                case 5:
                    findCustomersStartingWith(scanner, connection);
                    break;
                case 6:
                    calculateTotalOrderValue(connection);
                    break;
                case 7:
                    findProductsInPriceRange(scanner, connection);
                    break;
                case 8:
                    countTotalCustomers(connection);
                    break;
                case 9:
                    calculateAverageProductPrice(connection);
                    break;
                case 10:
                    findCustomerWithMostOrders(connection);
                    break;
                case 11:
                    calculateAverageOrderValue(connection);
                    break;
                case 12:
                    System.out.println("Выход из программы");
                    break;
                default:
                    System.out.println("Неверный ввод! Пожалуйста, выберите номер из меню.");
            }while (choice!=13 ) ;
            closeConnection(connection);
           scanner.close();
        }}}








