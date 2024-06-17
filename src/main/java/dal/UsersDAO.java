package dal;

import model.Users;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends DBContext {

    public static void main(String[] args) {
        UsersDAO dao = new UsersDAO();
        Users user = dao.checkAccountExist("thuxidau");
        System.out.println(user);
    }


    public Users check(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ? and password = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Users u = new Users(rs.getString("username"), rs.getString("fullname"), rs.getString("phonenumber"),
                        rs.getString("email"), rs.getInt("gender"), rs.getString("location"),
                        rs.getString("password"), rs.getString("dob"), rs.getInt("roleId"), rs.getString("image"),
                        rs.getString("address"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Users checkAccountExist(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Users u = new Users(rs.getString("username"), rs.getString("fullname"), rs.getString("phonenumber"),
                        rs.getString("email"), rs.getInt("gender"), rs.getString("location"),
                        rs.getString("password"), rs.getString("dob"), rs.getInt("roleId"), rs.getString("image"),
                        rs.getString("address"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void signUp(String username, String password, String email) throws SQLException {
        String sql = "INSERT INTO Users (username, email, gender, password, roleId) "
                + "VALUES (?,?,0,?,1)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, email);
            st.setString(3, password);
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void editUser(String fullname, String phone, String email, int gender, String location,
            String dob, String address, String username) {
        String sql = "UPDATE Users\n"
                + "SET fullname = ?,\n"
                + "    phoneNumber = ?,\n"
                + "    email = ?,\n"
                + "    gender = ?,\n"
                + "    location = ?,\n"
                + "    dob = ?,\n"
                + "    address = ?\n"
                + "WHERE username = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullname);
            st.setString(2, phone);
            st.setString(3, email);
            st.setInt(4, gender);
            st.setString(5, location);
            st.setString(6, dob);
            st.setString(7, address);
            st.setString(8, username);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updatePassword(Users user) {
        String sql = "UPDATE Users\n"
                + "SET password = ?\n"
                + "WHERE username = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user.getPassword());
            st.setString(2, user.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Users> getAll() {
        List<Users> list = new ArrayList<>();
        String sql = "select * from Users";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Users(rs.getString("username"), rs.getString("fullname"),
                        rs.getString("phoneNumber"), rs.getString("email"),
                        rs.getInt("gender"), rs.getString("location"),
                        rs.getString("password"), rs.getString("dob"), rs.getInt("roleId"),
                        rs.getString("image"), rs.getString("address")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void makeAdmin(String username) {
        String sql = "UPDATE Users\n"
                + "SET roleId = 0\n"
                + "WHERE username = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removeAdmin(String username) {
        String sql = "UPDATE Users \n"
                + "SET roleId = 1 \n"
                + "WHERE username = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removeAccount(String username) {
        String sql = "DELETE FROM Users\n"
                + "WHERE username = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Users> pagingUser(int index) {
        UsersDAO ud = new UsersDAO();
        List<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM Users\n"
                + "ORDER BY username\n"
                + "LIMIT 10 OFFSET ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 10);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Users(rs.getString("username"), rs.getString("fullname"),
                        rs.getString("phoneNumber"), rs.getString("email"),
                        rs.getInt("gender"), rs.getString("location"),
                        rs.getString("password"), rs.getString("dob"), rs.getInt("roleId"),
                        rs.getString("image"), rs.getString("address")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}