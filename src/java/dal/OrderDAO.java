package dal;

import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Item;
import model.Order;
import model.Users;

public class OrderDAO extends DBContext {

    public void addOrder(Users user, Cart cart) {
//        add to Order table
        LocalDate current_date = java.time.LocalDate.now();
        String date = current_date.toString();
        try {
            String sql = "insert into [Order] values (?,?,?,0)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, date);
            st.setString(2, user.getUsername());
            st.setFloat(3, cart.getTotalMoney());
            st.executeUpdate();

//            add to OrderDetail
            String sql1 = "select top 1 id from [Order] order by id desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            if (rs.next()) {
                // lay id cua order vua add
                int oid = rs.getInt(1);
                for (Item item : cart.getItems()) {
                    String sql2 = "insert into OrderDetail values (?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, item.getProduct().getId());
                    st2.setInt(2, oid);
                    st2.setInt(3, item.getQuantity());
                    st2.setFloat(4, item.getPrice());
                    st2.executeUpdate();
                }
            }

//          (update)  + qtysold, - quantity
            String sql3 = "select top 1 OrderID from OrderDetail order by OrderID desc";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            ResultSet rs3 = st3.executeQuery();
            if (rs3.next()) {
                // lay id cua orderdetail vua add
                int oid = rs.getInt(1);
                String sql4 = "UPDATE Product\n"
                        + "SET quantity = Product.quantity - OrderDetail.quantity,\n"
                        + "    qtysold = qtysold + OrderDetail.quantity\n"
                        + "FROM Product\n"
                        + "INNER JOIN OrderDetail ON Product.id = OrderDetail.ProductID\n"
                        + "WHERE OrderDetail.OrderID = ?";
                PreparedStatement st4 = connection.prepareStatement(sql4);
                st4.setInt(1, oid);
                st4.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private UsersDAO ud = new UsersDAO();

    public Order getOrderById(String id) {
        String sql = "select * from [Order] where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Users u = ud.checkAccountExist(rs.getString("username"));
                return (new Order(rs.getInt("id"), rs.getString("date"),
                        u, rs.getFloat("totalMoney"), rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void getPaid(String id) {
        try {
            String sql = "UPDATE [dbo].[Order] SET [status] = 1 WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public float getEarn() {
        String sql = "select sum(totalMoney) from [Order]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getTotalOrders() {
        String sql = "select count(id) from [Order]";
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
    
    public int getTotalOrdersByUser(String user) {
        String sql = "select count(id) from [Order] where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
}