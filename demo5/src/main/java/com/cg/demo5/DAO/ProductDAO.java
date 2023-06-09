package com.cg.demo5.DAO;

import com.cg.demo5.model.Category;
import com.cg.demo5.model.Product;


import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends ConnettionDatabase {
private static final String SELECT_PRODUCT = "SELECT p.*, c.`name` as category_name FROM product p left join category c on p.categoryid = c.idcategory;";
private static final String INSERT_PRODUCT = "INSERT INTO `md3product`.`product` (`name`, `price`, `quantity`, `categoryid`) VALUES (?, ?, ?, ?)";
private static final String EDIT_PRODUCT = "UPDATE `md3product`.`product` SET `name` = ?, `price` = ?, `quantity` = ?, `categoryid` = ? WHERE (`idproduct` = ?);\n";
private static final String SELECT_PRODUCT_EDIT_BY_ID = "SELECT p.*, c.`name` as category_name FROM product p left join category c on p.categoryid = c.idcategory \n" +
        "where p.idproduct = ?;";
private static final String DELETE_PRODUCT = " DELETE FROM `product` WHERE (`idproduct` = ?);";
    public List<Product> findAll() {
        List<Product> products= new ArrayList<>();
        // Step 1: tạo 1 kết nối xuống db để gọi câu lệnh SELECT or UPDATE, Delete, vv
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS);
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_PRODUCT);) {
            System.out.println(preparedStatement);
            // Step 3: tương đương vowis cái sét
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4:
            //kiểm tra còn data hay không. còn thì cứ lấy bằng câu lệnh ở dưới
            while (rs.next()) {
                //(truyên vào tên cột)
                int id = rs.getInt("idproduct");
                //(truyên vào tên cột)
                String name = rs.getString("name");
                //(truyên vào tên cột)
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int categoryid = rs.getInt("categoryid");
                String categoryname = rs.getString("category_name");
                products.add(new Product(id,name,price,quantity,new Category(categoryid,categoryname)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }



    public void insertProduct(Product product) {

        try (Connection connection = getConnection();
//            Giống như viết câu query trong mysql, nhưng chưa được thực thi
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setInt(4,product.getCategory().getId());

            System.out.println(preparedStatement);

//            Đây là hành động để thực thi câu lệnh
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void editProduct(Product product) {

        try (Connection connection = getConnection();
//            Giống như viết câu query trong mysql, nhưng chưa được thực thi
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setInt(4, product.getCategory().getId());
            preparedStatement.setInt(5 ,product.getId());
            System.out.println(preparedStatement);

//            Đây là hành động để thực thi câu lệnh
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Product findById(int id) {
        // Step 1: tạo 1 kết nối xuống db để gọi câu lệnh SELECT or UPDATE, Delete, vv
        try (
                Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS);
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_PRODUCT_EDIT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: tương đương vowis cái sét
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4:
            //kiểm tra còn data hay không. còn thì cứ lấy bằng câu lệnh ở dưới
            while (rs.next()) {
                //(truyên vào tên cột)
                int idProduct = rs.getInt("idproduct");
                //(truyên vào tên cột)
                String name = rs.getString("name");
                //(truyên vào tên cột)
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int categoryid = rs.getInt("categoryid");
                String categoryname = rs.getString("category_name");
                return new Product(id,name,price,quantity,new Category(categoryid,categoryname));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void deteleUse(int id ){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



}


