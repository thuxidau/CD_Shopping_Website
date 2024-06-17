package dal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UsersDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @InjectMocks
    private UsersDAO usersDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource({
            "testuser1, password1, testuser1@example.com",
            "thuxidau, password1, testuser1@example.com",
            ", password1, testuser1@example.com",
            "testuser1,, testuser1@example.com",
            "testuser1, password1,",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa, password1, testuser1@example.com",
            "testuser1, aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa, testuser1@example.com",
            "testuser1, password1, aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    })
    void testSignUp(String username, String password, String email) throws SQLException {
        String sql = "INSERT INTO Users (username, email, gender, password, roleId) "
                + "VALUES (?,?,0,?,1)";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        // Call the method to test
        usersDAO.signUp(username, password, email);

        // Verify the prepared statement interactions
        verify(preparedStatement, times(1)).setString(1, username);
        verify(preparedStatement, times(1)).setString(2, email);
        verify(preparedStatement, times(1)).setString(3, password);
        verify(preparedStatement, times(1)).executeUpdate();

        // Reset the interactions for the next test case
        reset(preparedStatement);
    }

    @ParameterizedTest
    @CsvSource({
            "'', password5, testuser5@example.com", // Boundary test: empty username
            "testuser6, '', testuser6@example.com", // Boundary test: empty password
            "testuser7, password7, ''", // Boundary test: empty email
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa, password8, testuser8@example.com", // Boundary test: long username
            "testuser9, aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa, testuser9@example.com", // Boundary test: long password
            "testuser10, password10, aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.aaaaaaaaaaaaaaaaa" // Boundary test: long email
    })
    void testSignUpInvalidCases(String username, String password, String email) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usersDAO.signUp(username, password, email);
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void testSignUpUsernameExists() throws SQLException {
        String sql = "INSERT INTO Users (username, email, gender, password, roleId) "
                + "VALUES (?,?,0,?,1)";
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        doThrow(new SQLException("Duplicate entry", "23000", 1062)).when(preparedStatement).executeUpdate();

        SQLException exception = assertThrows(SQLException.class, () -> {
            usersDAO.signUp("thuxidau", "password1", "testuser1@example.com");
        });

        assertTrue(exception.getMessage().contains("Duplicate entry"));
    }

    @ParameterizedTest
    @CsvSource({
            "hl14toi, VNHL, 0140140140, hl14toi@gmail.com, 0, Thủ Đức, 14tymuonnam, 1969-12-18, 1,, Long Phước, Quận 9",
            "thuxidau, Thu Doan, 0305070901, thudoan@fpt.edu.vn, 1, Hanoi, 123321, 2004-09-02, 0, ./img/avatar/myavt.jpg, 10 Khuong Ha, Khuong Dinh, Thanh Xuan",
            "user3, Alice Johnson, 1122334455, alicejohnson@example.com, 1, 789 Oak St, password789, 1992-03-03, 1, alice.jpg, 789 Oak St",
            ", Alice Johnson, 1122334455, alicejohnson@example.com, 1, 789 Oak St, password789, 1992-03-03, 1, alice.jpg, 789 Oak St"
    })
    void testCheckAccountExist(String username, String fullname, String phonenumber, String email, int gender, String location, String password, String dob, int roleId, String image, String address) throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        String sql = "SELECT * FROM Users WHERE username = ?;";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("username")).thenReturn(username);
        when(resultSet.getString("fullname")).thenReturn(fullname);
        when(resultSet.getString("phonenumber")).thenReturn(phonenumber);
        when(resultSet.getString("email")).thenReturn(email);
        when(resultSet.getInt("gender")).thenReturn(gender);
        when(resultSet.getString("location")).thenReturn(location);
        when(resultSet.getString("password")).thenReturn(password);
        when(resultSet.getString("dob")).thenReturn(dob);
        when(resultSet.getInt("roleId")).thenReturn(roleId);
        when(resultSet.getString("image")).thenReturn(image);
        when(resultSet.getString("address")).thenReturn(address);

        // Call the method under test
        Users user = usersDAO.checkAccountExist(username);

        // Verify the interactions with the mocks
        verify(connection, times(1)).prepareStatement(sql);
        verify(preparedStatement, times(1)).setString(1, username);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(1)).next();

        // Validate the result
        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(fullname, user.getFullname());
        assertEquals(phonenumber, user.getPhoneNumber());
        assertEquals(email, user.getEmail());
        assertEquals(gender, user.getGender());
        assertEquals(location, user.getLocation());
        assertEquals(password, user.getPassword());
        assertEquals(dob, user.getDob());
        assertEquals(roleId, user.getRoleId());
        assertEquals(image, user.getImage());
        assertEquals(address, user.getAddress());

        // Reset the mocks for the next test case
        reset(connection, preparedStatement, resultSet);
    }

    @ParameterizedTest
    @CsvSource({
            "VNHL, 0140140140, hl14toi@gmail.com, 0, Thủ Đức, 14tymuonnam, 1969-12-18, 1,, Long Phước, Quận 9, hl14toi",
            "Thu Doan, 0305070901, thudoan@fpt.edu.vn, 1, Hanoi, 123321, 2004-09-02, 0, ./img/avatar/myavt.jpg, 10 Khuong Ha, Khuong Dinh, Thanh Xuan, thuxidau",
            "Alice Johnson, 1122334455, alicejohnson@example.com, 1, 789 Oak St, password789, 1992-03-03, 1, alice.jpg, 789 Oak St, user3",
            "Alice Johnson, 1122334455, alicejohnson@example.com, 1, 789 Oak St, password789, 1992-03-03, 1, alice.jpg, 789 Oak St,"
    })
    void testEditUser(String fullname, String phone, String email, int gender, String location,
                      String dob, String address, String username) throws SQLException {
        String sql = "UPDATE Users\n"
                + "SET fullname = ?,\n"
                + "    phoneNumber = ?,\n"
                + "    email = ?,\n"
                + "    gender = ?,\n"
                + "    location = ?,\n"
                + "    dob = ?,\n"
                + "    address = ?\n"
                + "WHERE username = ?;";

        // Mock the behavior of the connection to return the prepared statement
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        // Call the method under test
        usersDAO.editUser(fullname, phone, email, gender, location, dob, address, username);

        // Verify the interactions with the mocks
        verify(connection, times(1)).prepareStatement(sql);
        verify(preparedStatement, times(1)).setString(1, fullname);
        verify(preparedStatement, times(1)).setString(2, phone);
        verify(preparedStatement, times(1)).setString(3, email);
        verify(preparedStatement, times(1)).setInt(4, gender);
        verify(preparedStatement, times(1)).setString(5, location);
        verify(preparedStatement, times(1)).setString(6, dob);
        verify(preparedStatement, times(1)).setString(7, address);
        verify(preparedStatement, times(1)).setString(8, username);
        verify(preparedStatement, times(1)).executeUpdate();

        // Reset the mocks for the next test case
        reset(connection, preparedStatement);
    }
}