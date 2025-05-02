package org.example;

import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;

import static org.example.Properties.*;

public class DDD {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        connection.close();
    }

    static Scanner scanner = new Scanner(System.in);

    public static void insertCustomers(Connection connection) throws SQLException {
        String sql = "INSERT INTO customers (id,name,email,registered_at) VALUES (?,?,?,?)";

        System.out.println("Введите id: ");
        int id = scanner.nextInt();

        System.out.println("Введите имя: ");
        String name = scanner.next();

        System.out.println("Введите Email: ");
        String email = scanner.next();

        System.out.println("Введите дату регистрации: ");
        Timestamp registered_at = Timestamp.valueOf(scanner.next());

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setTimestamp(4, registered_at);

            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void deleteCustomers(Connection connection) {
        String sql = "DELETE FROM customers WHERE id = ?";
        System.out.println("Введите id: ");
        int id = scanner.nextInt();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public static void updateCustomers(Connection connection) {
        String sql = "UPDATE customers SET name = ? WHERE id = ?";
        System.out.println("Введите id: ");
        int id = scanner.nextInt();

        System.out.println("Введите имя");
        String name = scanner.next();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, id);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertOrders(Connection connection) throws SQLException {
        String sql = "INSERT INTO orders (id,customer_id,product_id,quantity,order_date) VALUES (?,?,?,?,?)";

        System.out.println("Введите id: ");
        int id = scanner.nextInt();

        System.out.println("Введите id Пользователя: ");
        int customer_id = scanner.nextInt();

        System.out.println("Введите id Продукта: ");
        int product_id = scanner.nextInt();

        System.out.println("Введите количество: ");
        int quantity = scanner.nextInt();

        System.out.println("Введите дату регистрации: ");
        Timestamp order_date = Timestamp.valueOf(scanner.next());
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, customer_id);
            statement.setInt(3, product_id);
            statement.setInt(4, quantity);
            statement.setTimestamp(5, order_date);

            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void deleteOrders(Connection connection) {
        String sql = "DELETE FROM orders WHERE id = ?";
        System.out.println("Введите id: ");
        int id = scanner.nextInt();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void updateOrders(Connection connection) {
        String sql = "UPDATE orders SET quantity = ? WHERE id = ?";
        System.out.println("Введите id: ");
        int id = scanner.nextInt();

        System.out.println("Введите Количество: ");
        int quantity = scanner.nextInt();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,quantity);
            statement.setInt(2, id);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }}

