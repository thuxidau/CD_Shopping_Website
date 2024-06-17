package dal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CategoryDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private CategoryDAO categoryDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource({
            "1, CD",
            "2, VINYL",
            "3, CASSETTE",
            "4,"
    })
    void testFindCategoryById(int id, String name) throws SQLException {
        String sql = "select * from Category where id = ?;";
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(id);
        when(resultSet.getString("name")).thenReturn(name);

        Category category = categoryDAO.findCategoryById(id);

        assertNotNull(category);
        assertEquals(id, category.getId());
        assertEquals(name, category.getName());

        verify(preparedStatement, times(1)).setInt(1, id);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(1)).next();
        verify(resultSet, times(1)).getInt("id");
        verify(resultSet, times(1)).getString("name");

        reset(preparedStatement, resultSet);
    }

    @Test
    void testFindCategoryById_CategoryNotFound() throws SQLException {
        String sql = "select * from Category where id = ?;";
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(false);

        Category category = categoryDAO.findCategoryById(999);

        assertNull(category);

        verify(preparedStatement, times(1)).setInt(1, 999);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(1)).next();
    }
}
