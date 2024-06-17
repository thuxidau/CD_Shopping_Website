package dal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Category;
import model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CountryDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private CountryDAO countryDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource({
            "1, US-UK",
            "2, Korea",
            "3, VN",
            "4,"
    })
    void testGetCountryById(int id, String name) throws SQLException {
        String sql = "SELECT * FROM Country WHERE id = ?;";
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(id);
        when(resultSet.getString("name")).thenReturn(name);

        Country country = countryDAO.getCountryById(id);

        assertNotNull(country);
        assertEquals(id, country.getId());
        assertEquals(name, country.getName());

        verify(preparedStatement, times(1)).setInt(1, id);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(1)).next();
        verify(resultSet, times(1)).getInt("id");
        verify(resultSet, times(1)).getString("name");

        reset(preparedStatement, resultSet);
    }

    @ParameterizedTest
    @CsvSource({
            "1000",
            "2000",
            "3000"
    })
    void testGetCountryById_NotFound(int id) throws SQLException {
        String sql = "SELECT * FROM Country WHERE id = ?;";
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(false);

        Country country = countryDAO.getCountryById(id);

        assertNull(country);

        verify(preparedStatement, times(1)).setInt(1, id);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(1)).next();

        reset(preparedStatement, resultSet);
    }
}