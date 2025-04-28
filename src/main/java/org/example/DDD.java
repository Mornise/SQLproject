package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static org.example.Properties.*;

public class DDD {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        connection.close();
    }
    static Scanner scanner = new Scanner(System.in);
    public static void  insert(Connection connection) throws SQLException {
        String sql = "INSERT INTO products (id,name,price,stock) VALUES (?,?,?,?)";

        System.out.println("Введите id: ");
        String id = String.valueOf(scanner.nextInt());

        System.out.println("Введите имя: ");
        String name = scanner.next();

        System.out.println("Введите прайс: ");
        int price = scanner.nextInt();

        System.out.println("Введите количество: ");
        int stock = scanner.nextInt();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(id));
            statement.setString(2, name);
            statement.setInt(3, price);
            statement.setInt(4, stock);

            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

public static void deleteProducts(Connection connection){
        String sql = "DELETE FROM products WHERE id = ?";
        System.out.println("Введите id: ");
        String id = String.valueOf(scanner.nextInt());
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(id));
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println(row);}
    } catch (SQLException ex) {
            ex.printStackTrace();
        }


}public  static  void update(Connection connection){
        String sql = "UPDATE products SET name = ? WHERE id = ?";
        System.out.println("Введите id: ");
        String id = String.valueOf(scanner.nextInt());

        System.out.println("Введите имя");
        String name = scanner.next();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, Integer.parseInt(id));
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    }





