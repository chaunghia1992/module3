package com.cg.demo5.DAO;

import com.cg.demo5.model.Category;
import com.cg.demo5.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CategoryDAO extends ConnettionDatabase {
    private static final String SELECT_PRODUCT = "SELECT * FROM category;";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM category where idcategory=?;";

    //    private static final String INSERT_PRODUCT = "insert into `product`(name,price,quantity) values (?,?,?);";
    public List<Category> findAll() {
        List<Category> products = new ArrayList<>();
        // Step 1: tạo 1 kết nối xuống db để gọi câu lệnh SELECT or UPDATE, Delete, vv
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_PRODUCT);) {
            System.out.println(preparedStatement);
            // Step 3: tương đương vowis cái sét
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4:
            //kiểm tra còn data hay không. còn thì cứ lấy bằng câu lệnh ở dưới
            while (rs.next()) {
                //(truyên vào tên cột)
                int id = rs.getInt("idcategory");
                //(truyên vào tên cột)
                String name = rs.getString("name");
                //(truyên vào tên cột)
                products.add(new Category(id,name));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }
    public Category findById(int id) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_PRODUCT_BY_ID);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);

            // Step 3: tương đương vowis cái sét
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4:
            //kiểm tra còn data hay không. còn thì cứ lấy bằng câu lệnh ở dưới
            while (rs.next()) {
                //(truyên vào tên cột)
                int idcate = rs.getInt("idcategory");
                //(truyên vào tên cột)
                String name = rs.getString("name");
                //(truyên vào tên cột)
                return new Category(idcate,name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
