package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Country;

public class CountryDAO extends DBContext{
    public Country getCountryById(int cid) {
        String sql = "SELECT * "
                + "  FROM [dbo].[Country] where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            //1
            if (rs.next()) {
                Country c = new Country();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<Country> getAll() {
        List<Country> list = new ArrayList<>();
        String sql = "select * from Country";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Country(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
