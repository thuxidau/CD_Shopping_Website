package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Country;
import model.Product;

public class ProductDAO extends DBContext {

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<Product> p = dao.getRelatedProducts("5","28");
        for (Product p1 : p) {
            System.out.println(p1);
        }
    }

    private CategoryDAO cd = new CategoryDAO();
    private CountryDAO ctd = new CountryDAO();

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = cd.findCategoryById(rs.getInt("categoryId"));
                Country ct = ctd.getCountryById(rs.getInt("countryId"));
                Product p = new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getBoolean("status"),
                        c,
                        ct,
                        rs.getInt(8));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> getProductById(String cid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE categoryId = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = cd.findCategoryById(rs.getInt("categoryId"));
                Country ct = ctd.getCountryById(rs.getInt("countryId"));
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getBoolean("status"),
                        c,
                        ct,
                        rs.getInt(8)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Product findProductById(String pid) {
        String sql = "select * from Product where id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = cd.findCategoryById(rs.getInt("categoryId"));
                Country ct = ctd.getCountryById(rs.getInt("countryId"));
                return (new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getBoolean("status"),
                        c,
                        ct,
                        rs.getInt(8)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> pagingSearchProduct(String name, int index) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE `name` LIKE ? OR `description` LIKE ? ORDER BY id LIMIT 12 OFFSET ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            st.setString(2, "%" + name + "%");
            st.setInt(3, (index - 1) * 12);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = cd.findCategoryById(rs.getInt("categoryId"));
                Country ct = ctd.getCountryById(rs.getInt("countryId"));
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getBoolean("status"),
                        c,
                        ct,
                        rs.getInt(8)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int searchCount(String name) {
        int count = 0;
        String sql = "SELECT COUNT(*) \n"
                + "FROM Product \n"
                + "WHERE `name` LIKE ? OR description LIKE ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            st.setString(2, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return count;
    }

    public int totalProductCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) \n"
                + "FROM Product;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return count;
    }

    public List<Product> pagingProduct(int index) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product\n"
                + "ORDER BY id desc\n"
                + "LIMIT 10 OFFSET ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 10);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = cd.findCategoryById(rs.getInt("categoryId"));
                Country ct = ctd.getCountryById(rs.getInt("countryId"));
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getBoolean("status"),
                        c,
                        ct,
                        rs.getInt(8)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int totalProductCountByCategory(String id) {
        int count = 0;
        String sql = "SELECT COUNT(*) \n"
                + "FROM Product where categoryId = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return count;
    }

    public void deleteProduct(String id) {
        String sql = "delete from Product where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addProduct(String name, String price, String quantity, String description,
            String image, String category, String country) {
        String sql = "INSERT INTO Product (name,price,quantity,description,image,status,categoryId,countryId,qtysold) VALUES"
                + "(?,?,?,?,?,1,?,?,0)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, price);
            st.setString(3, quantity);
            st.setString(4, description);
            st.setString(5, image);
            st.setString(6, category);
            st.setString(7, country);
            st.executeUpdate();
            System.out.println("added successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void editProduct(String name, String price, String quantity, String description,
            String image, String status, String category, String country, String pid) {
        String sql = "UPDATE Product\n"
                + "SET `name` = ?,\n"
                + "    price = ?,\n"
                + "    quantity = ?,\n"
                + "    description = ?,\n"
                + "    image = ?,\n"
                + "    status = ?,\n"
                + "    categoryId = ?,\n"
                + "    countryId = ?\n"
                + "WHERE id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, price);
            st.setString(3, quantity);
            st.setString(4, description);
            st.setString(5, image);
            st.setString(6, status);
            st.setString(7, category);
            st.setString(8, country);
            st.setString(9, pid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Product> getNewArrivalProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product ORDER BY id DESC LIMIT 10;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = cd.findCategoryById(rs.getInt("categoryId"));
                Country ct = ctd.getCountryById(rs.getInt("countryId"));
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getBoolean("status"),
                        c,
                        ct,
                        rs.getInt(8)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTotalProduct() {
        String sql = "select sum(quantity) from Product;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getTotalProductSold() {
        String sql = "select sum(qtysold) from Product;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Product> getBestSellingProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product ORDER BY qtysold DESC LIMIT 10;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = cd.findCategoryById(rs.getInt("categoryId"));
                Country ct = ctd.getCountryById(rs.getInt("countryId"));
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getBoolean("status"),
                        c,
                        ct,
                        rs.getInt("qtysold")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getLimitedEditionProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE `name` LIKE '%limited edition%' ORDER BY id DESC LIMIT 10;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = cd.findCategoryById(rs.getInt("categoryId"));
                Country ct = ctd.getCountryById(rs.getInt("countryId"));
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getBoolean("status"),
                        c,
                        ct,
                        rs.getInt("qtysold")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getRelatedProducts(String cid, String pid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE categoryId = ? AND id != ? ORDER BY RAND() LIMIT 4;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            st.setString(2, pid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = cd.findCategoryById(rs.getInt("categoryId"));
                Country ct = ctd.getCountryById(rs.getInt("countryId"));
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getBoolean("status"),
                        c,
                        ct,
                        rs.getInt("qtysold")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> getTop5US_UK() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE countryId = 1 ORDER BY RAND() LIMIT 5;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = cd.findCategoryById(rs.getInt("categoryId"));
                Country ct = ctd.getCountryById(rs.getInt("countryId"));
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getBoolean("status"),
                        c,
                        ct,
                        rs.getInt("qtysold")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> getTop5K_POP() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE countryId = 2 ORDER BY RAND() LIMIT 5;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = cd.findCategoryById(rs.getInt("categoryId"));
                Country ct = ctd.getCountryById(rs.getInt("countryId"));
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getBoolean("status"),
                        c,
                        ct,
                        rs.getInt("qtysold")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> getTop5V_POP() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE countryId = 3 ORDER BY RAND() LIMIT 5;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = cd.findCategoryById(rs.getInt("categoryId"));
                Country ct = ctd.getCountryById(rs.getInt("countryId"));
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getBoolean("status"),
                        c,
                        ct,
                        rs.getInt("qtysold")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
