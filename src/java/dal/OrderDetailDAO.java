package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderDetail;
import model.Product;

public class OrderDetailDAO extends DBContext {

    private ProductDAO pd = new ProductDAO();
    private OrderDAO od = new OrderDAO();

    public List<OrderDetail> getAll() {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "select * from OrderDetail order by OrderID asc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = pd.findProductById(rs.getString("ProductID"));
                Order o = od.getOrderById(rs.getString("OrderID"));
                OrderDetail od = new OrderDetail(p, o,
                        rs.getInt("quantity"), rs.getFloat("price"));
                list.add(od);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<OrderDetail> getOrderDetailByUser(String user) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "  select * from OrderDetail od\n"
                + "  join [Order] o on o.id = od.OrderID\n"
                + "  where o.username = ?\n"
                + "  order by o.id asc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = pd.findProductById(rs.getString("ProductID"));
                Order o = od.getOrderById(rs.getString("OrderID"));
                OrderDetail od = new OrderDetail(p, o,
                        rs.getInt("quantity"), rs.getFloat("price"));
                list.add(od);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<OrderDetail> pagingOrder(int index) {
        UsersDAO ud = new UsersDAO();
        List<OrderDetail> list = new ArrayList<>();
        String sql = "select * from OrderDetail\n"
                + "order by OrderID\n"
                + "offset ?  rows fetch next 10 rows only";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 10);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = pd.findProductById(rs.getString("ProductID"));
                Order o = od.getOrderById(rs.getString("OrderID"));
                list.add(new OrderDetail(p, o,
                        rs.getInt("quantity"), rs.getFloat("price")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<OrderDetail> pagingYourOrder(int index, String user) {
        UsersDAO ud = new UsersDAO();
        List<OrderDetail> list = new ArrayList<>();
        String sql = "select * from OrderDetail\n"
                + "join [Order] on [Order].id = OrderDetail.OrderID\n"
                + "where [Order].username = ?\n"
                + "order by OrderID\n"
                + "offset ?  rows fetch next 10 rows only";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setInt(2, (index - 1) * 10);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = pd.findProductById(rs.getString("ProductID"));
                Order o = od.getOrderById(rs.getString("OrderID"));
                list.add(new OrderDetail(p, o,
                        rs.getInt("quantity"), rs.getFloat("price")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}