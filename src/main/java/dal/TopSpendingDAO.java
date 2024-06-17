package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.TopSpending;

public class TopSpendingDAO extends DBContext {

    public List<TopSpending> getTop5Spending() {
        List<TopSpending> list = new ArrayList<>();
        try {
            String sql = "SELECT `Order`.username, Users.image, Users.fullname, Users.phoneNumber, SUM(totalMoney) AS total\n"
                    + "FROM `Order`\n"
                    + "JOIN Users ON Users.username = `Order`.username\n"
                    + "WHERE `Order`.status = 1\n"
                    + "GROUP BY `Order`.username, Users.image, Users.fullname, Users.phoneNumber\n"
                    + "ORDER BY total DESC\n"
                    + "LIMIT 5;";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new TopSpending(rs.getString("username"), rs.getString("image"), rs.getString("fullname"),
                        rs.getString("phoneNumber"), rs.getDouble("total")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}