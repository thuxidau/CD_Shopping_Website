package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.TopSpending;

public class TopSpendingDAO extends DBContext {

    public List<TopSpending> getTop5Spending() {
        List<TopSpending> list = new ArrayList<>();
        try {
            String sql = "select top 5 [Order].username, Users.image, Users.fullname, Users.phoneNumber, sum(totalMoney) as total\n"
                    + "  from [Order] \n"
                    + "  join Users on Users.username = [Order].username\n"
                    + " where [Order].status = 1\n"
                    + "  group by [Order].username, Users.image, Users.fullname, Users.phoneNumber\n"
                    + "  order by total desc\n";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(new TopSpending(rs.getString("username"), rs.getString("image"), rs.getString("fullname"),
                        rs.getString("phoneNumber"), rs.getDouble("total")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
