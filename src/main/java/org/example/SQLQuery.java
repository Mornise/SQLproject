package org.example;
import java.sql.*;
import java.util.Scanner;
public class SQLQuery  {

    public SQLQuery() throws SQLException {}

    public static void displayAllProducts(Connection connection) {
        String query = "SELECT * FROM products";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(rs.getString("name") + " " + rs.getBigDecimal("price") + " " + rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
    }
    public static void displayAllCustomers(Connection connection) {
        String query = "SELECT * FROM customers";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(rs.getString("name")+ " " +  rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
    }
    public static void findMostExpensiveProduct(Connection connection) {
        String query = "SELECT * FROM products ORDER BY price DESC LIMIT 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                System.out.printf(rs.getString("name"),  rs.getBigDecimal("price"),  rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
    }

    public static void findProductsInStockGreaterThanN(Scanner scanner,Connection connection) {
        System.out.print("Введите количество N: ");
        int n = scanner.nextInt();

        String query = "SELECT * FROM products WHERE stock > ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, n);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("name")  + " " +  rs.getBigDecimal("price") + " " + rs.getInt("stock"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
    }
    public static void findCustomersStartingWith(Scanner scanner,Connection connection) {
        System.out.print("Введите букву: ");
        String letter = scanner.nextLine();

        String query = "SELECT * FROM customers WHERE name LIKE ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, letter + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf(rs.getString("name"), rs.getString("email"), rs.getTimestamp("registered_at"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
    }
    public static void calculateTotalOrderValue(Connection connection) {
        String query = "SELECT SUM(p.price * o.quantity) AS total_value FROM orders o JOIN products p ON o.product_id = p.id";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                System.out.printf("Суммарная стоимость всех заказов: %.2f%n", rs.getBigDecimal("total_value"));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
    }
    public static void findProductsInPriceRange(Scanner scanner,Connection connection) {
        System.out.print("Введите минимальную цену: ");
        double minPrice = scanner.nextDouble();

        if(minPrice < 0) {
            System.err.println("Цена не может быть отрицательной!");
            return;
        }
        System.out.print ("Введите максимальную цену: ");
        double maxPrice= scanner.nextDouble();

        if(maxPrice < minPrice){
            System.err.println ("Максимальная цена не может быть меньше минимальной!");
            return;
        }

        String query= "SELECT * FROM products WHERE price BETWEEN ? AND ?";

        try(PreparedStatement pstmt= connection.prepareStatement(query)){
            pstmt.setDouble(1,minPrice);
            pstmt.setDouble(2,maxPrice);

            try(ResultSet rs= pstmt.executeQuery()){
                while(rs.next()){
                    System.out.printf(rs.getString (" name"), rs.getBigDecimal ("price"), rs.getInt ("stock"));
                }
            }
        }catch(SQLException e){
            System.out.println("Ошибка");
        }
    }
    public static void countTotalCustomers(Connection connection) {
        String query= "SELECT COUNT(*) AS total_customers FROM customers";

        try(Statement stmt= connection.createStatement(); ResultSet rs= stmt.executeQuery(query)){
            if(rs.next()){
                System.out.printf ("Общее количество клиентов: %d%n",rs.getInt ("total_customers"));
            }
        }catch(SQLException e){
            System.out.println("Ошибка");
        }
    }
    public static void calculateAverageProductPrice(Connection connection) {
        String query= "SELECT AVG(price) AS average_price FROM products";

        try(Statement stmt= connection.createStatement(); ResultSet rs= stmt.executeQuery(query)){
            if(rs.next()){
                System.out.printf ("Средняя цена товаров: %.2f%n",rs.getBigDecimal ("average_price"));
            }
        }catch(SQLException e){
            System.out.println("Ошибка");
        }
    }
    public static void findCustomerWithMostOrders(Connection connection) {
        String query= "SELECT c.id, c.name, COUNT(o.customer_id) AS order_count "
                + "FROM customers c LEFT JOIN orders o ON c.id=o.customer_id "
                + "GROUP BY c.id ORDER BY order_count DESC LIMIT 1";

        try(Statement stmt= connection.createStatement(); ResultSet rs= stmt.executeQuery(query)){
            if(rs.next()){
                System.out.printf ("%d | %s | Заказов: %d%n",rs.getInt ("id"),
                        rs.getString ("name"),rs.getInt ("order_count"));
            }
        }catch(SQLException e){
            System.out.println("Ошибка");
        }
    }
    public static void calculateAverageOrderValue(Connection connection) {
        String query="SELECT AVG(total_order_value) AS average_order_value "
                + "FROM (SELECT SUM(p.price * o.quantity) AS total_order_value "
                + "FROM orders o JOIN products p ON o.product_id=p.id GROUP BY o.order_date) AS order_totals";

        try(Statement stmt=connection.createStatement(); ResultSet rs=stmt.executeQuery(query)){
            if(rs.next()){
                System.out.printf ("Средняя сумма заказа: %.2f%n",rs.getBigDecimal ("average_order_value"));
            }
        }catch(SQLException e){
            System.out.println("Ошибка");
        }
    }
    public static void closeConnection(Connection connection) {
        if(connection != null){
            try{
                connection.close ();
            }catch(SQLException ex) {
                System.out.println("Ошибка");
            }
        }
    }
}














