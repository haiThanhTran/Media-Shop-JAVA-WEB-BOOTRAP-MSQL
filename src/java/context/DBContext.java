package context;

import entity.Category;
import entity.Product;
import entity.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author FPT University - PRJ30X
 */
public class DBContext {

    protected Connection connection;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public DBContext() {
        //@Students: You are allowed to edit user, pass, url variables to fit 
        //your system configuration
        //You can also add more methods for Database Interaction tasks. 
        //But we recommend you to do it in another class
        // For example : StudentDBContext extends DBContext , 
        //where StudentDBContext is located in dal package, 
        try {
            String user = "sa";
            String pass = "123";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=shop";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                ));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<Product> getCategoryByCid(String cid) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product where product.cateID=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cid);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                ));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<Product> getProductBySearch(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "select *\n"
                + "from product p\n"
                + "where p.name like ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + txtSearch + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                ));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public Account login(String user, String pass) {

        String query = "select *\n"
                + "from account a\n"
                + "where a.[user]=? and a.pass=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, pass);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)
                );

            }
        } catch (Exception e) {

        }
        return null;
    }

    public Account signup(String user, String pass) {

        String query = "insert into account\n"
                + "values(?,?,0,0)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, pass);
            statement.executeUpdate();
            connection.commit();
            
           
        } catch (Exception e) {

        }
        return null;
    }

    public Account checkExistAccount(String user) {
        String query = "SELECT * FROM account WHERE account.[user] = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)
                );
            }
        } catch (Exception e) {
            e.printStackTrace(); // Bạn cần xử lý exception ở đây, ví dụ như log lỗi
        }
        return null;
    }

    public Product getProductByPid(String pid) {

        String query = "select * from product where product.id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, pid);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
            }
        } catch (Exception e) {

        }
        return null;
    }

    public Product getNewProduct() {

        String query = "select top 1 * from product order by product.cateID desc";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
            }
        } catch (Exception e) {

        }
        return null;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                list.add(
                        new Category(rs.getInt(1),
                                rs.getString(2)));

            }
        } catch (Exception e) {

        }
        return list;
    }

}
