package dal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import model.Category;
import model.Country;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDAOTest {

    @InjectMocks
    private ProductDAO productDAO;

    @Mock
    private Connection connection;

    @Mock
    private ResultSet resultSet;

    @Mock
    private CategoryDAO cd;

    @Mock
    private CountryDAO ctd;

    @Mock
    private PreparedStatement preparedStatement;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 28, Honeymoon, 19.99, 10, Lana Del Rey - Honeymoon, testimage.jpg, true, 1, 1",
            "2, 30, Born To Die, 12, 12, Lana Del Rey, abc.jpeg, true, 2, 1",
            "3, 16, HOÀNG THUỲ LINH - LINK (BURNT ORANGE), 15.49, 204, Hoàng Thuỳ Linh - LINK (Burnt Orange) - Băng Cassette Nhạc Việt Nam, https://product.hstatic.net/1000304920/product/hoang-thuy-linh-link-burnt-orange-bang-cassette_ef5f69ed9a124a6189e2dbe5f8c85f32.jpg, true, 3, 3",
            "4, 33, abc, 12, 12, abc, abc, true, 1, 1"
    })
    void testGetProductById(String cid, int productId, String productName, float price, int quantity,
                            String description, String image, boolean status, int categoryId, int countryId) throws SQLException {

        // Set up the mocks
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        if (cid.equals("4")) {
            // Simulate the ResultSet returning no rows
            when(resultSet.next()).thenReturn(false);
        } else {
            // Simulate the ResultSet behavior
            when(resultSet.next()).thenReturn(true).thenReturn(false);
            when(resultSet.getInt("id")).thenReturn(productId);
            when(resultSet.getString("name")).thenReturn(productName);
            when(resultSet.getFloat("price")).thenReturn(price);
            when(resultSet.getInt("quantity")).thenReturn(quantity);
            when(resultSet.getString("description")).thenReturn(description);
            when(resultSet.getString("image")).thenReturn(image);
            when(resultSet.getBoolean("status")).thenReturn(status);
            when(resultSet.getInt("categoryId")).thenReturn(categoryId);
            when(resultSet.getInt("countryId")).thenReturn(countryId);

            // Mock the Category and Country DAOs
            Category mockCategory = new Category(categoryId, "CategoryName");
            Country mockCountry = new Country(countryId, "CountryName");

            when(cd.findCategoryById(categoryId)).thenReturn(mockCategory);
            when(ctd.getCountryById(countryId)).thenReturn(mockCountry);
        }

        // Call the method to test
        List<Product> products = productDAO.getProductById(cid);

        if (cid.equals("4")) {
            // Verify the results for the case returning an empty list
            assertNotNull(products);
            assertTrue(products.isEmpty());
        } else {
            // Verify the results
            assertNotNull(products);
            assertEquals(1, products.size());

            Product product = products.get(0);
            assertEquals(productId, product.getId());
            assertEquals(productName, product.getName());
            assertEquals(price, product.getPrice());
            assertEquals(quantity, product.getQuantity());
            assertEquals(description, product.getDescription());
            assertEquals(image, product.getImage());
            assertEquals(status, product.isStatus());
            assertEquals(categoryId, product.getCategory().getId());
            assertEquals(countryId, product.getCountry().getId());
        }

        // Verify interactions with mocks
        verify(preparedStatement).setString(1, cid);
        verify(preparedStatement).executeQuery();
        verify(resultSet, atLeastOnce()).next();

        if (!cid.equals("4")) {
            verify(cd).findCategoryById(categoryId);
            verify(ctd).getCountryById(countryId);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "moon, 1, 28, Honeymoon, 19.99, 10, Lana Del Rey - Honeymoon, testimage.jpg, true, 1, 1",
            "t, 2, 30, Born To Die, 12, 12, Lana Del Rey, abc.jpeg, true, 2, 1",
            "reeeeey, 1,,,,,,,,,",
            ", 2, 30, Born To Die, 12, 12, Lana Del Rey, abc.jpeg, true, 2, 1",
            "moon, 50,,,,,,,,,"
    })
    void testPagingSearchProduct(String searchName, int index, Integer productId, String productName, Float price, Integer quantity,
                                 String description, String image, Boolean status, Integer categoryId, Integer countryId) throws SQLException {

        // Set up the mocks
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        if (productId == null) {
            // Simulate the ResultSet returning no rows
            when(resultSet.next()).thenReturn(false);
        } else {
            // Simulate the ResultSet behavior
            when(resultSet.next()).thenReturn(true).thenReturn(false);
            when(resultSet.getInt("id")).thenReturn(productId);
            when(resultSet.getString("name")).thenReturn(productName);
            when(resultSet.getFloat("price")).thenReturn(price);
            when(resultSet.getInt("quantity")).thenReturn(quantity);
            when(resultSet.getString("description")).thenReturn(description);
            when(resultSet.getString("image")).thenReturn(image);
            when(resultSet.getBoolean("status")).thenReturn(status);
            when(resultSet.getInt("categoryId")).thenReturn(categoryId);
            when(resultSet.getInt("countryId")).thenReturn(countryId);

            // Mock the Category and Country DAOs
            Category mockCategory = new Category(categoryId, "CategoryName");
            Country mockCountry = new Country(countryId, "CountryName");

            when(cd.findCategoryById(categoryId)).thenReturn(mockCategory);
            when(ctd.getCountryById(countryId)).thenReturn(mockCountry);
        }

        // Call the method to test
        List<Product> products = productDAO.pagingSearchProduct(searchName, index);

        if (productId == null) {
            // Verify the results for the case returning an empty list
            assertNotNull(products);
            assertTrue(products.isEmpty());
        } else {
            // Verify the results for the regular cases
            assertNotNull(products);
            assertEquals(1, products.size());

            Product product = products.get(0);
            assertEquals(productId.intValue(), product.getId());
            assertEquals(productName, product.getName());
            assertEquals(price, product.getPrice());
            assertEquals(quantity.intValue(), product.getQuantity());
            assertEquals(description, product.getDescription());
            assertEquals(image, product.getImage());
            assertEquals(status.booleanValue(), product.isStatus());
            assertEquals(categoryId.intValue(), product.getCategory().getId());
            assertEquals(countryId.intValue(), product.getCountry().getId());
        }

        // Verify interactions with mocks
        verify(preparedStatement).setString(1, "%" + searchName + "%");
        verify(preparedStatement).setString(2, "%" + searchName + "%");
        verify(preparedStatement).setInt(3, (index - 1) * 12);
        verify(preparedStatement).executeQuery();
        verify(resultSet, atLeastOnce()).next();

        if (productId != null) {
            verify(cd).findCategoryById(categoryId);
            verify(ctd).getCountryById(countryId);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "taylor, 12",  // Product name 'Laptop' found twice
            "rey, 3",   // Product description contains 'Shoes'
            "aaaaaaaaaaaa, 0",    // No product with name or description containing 'Book'
            "'', 28"       // Empty search returns all products
    })
    void testSearchCount(String searchTerm, int expectedCount) throws SQLException {
        String sql = "SELECT COUNT(*) \n"
                + "FROM Product \n"
                + "WHERE `name` LIKE ? OR description LIKE ?;";
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(expectedCount);

        int actualCount = productDAO.searchCount(searchTerm);

        assertEquals(expectedCount, actualCount);

        verify(preparedStatement, times(1)).setString(1, "%" + searchTerm + "%");
        verify(preparedStatement, times(1)).setString(2, "%" + searchTerm + "%");
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(1)).next();
        verify(resultSet, times(1)).getInt(1);

        reset(preparedStatement, resultSet);
    }

    @ParameterizedTest
    @CsvSource({
            "Laptop, 999.99, 10, Powerful laptop, laptop.jpg, 1, 1",  // Normal case
            "Book, 20.0, 100, Interesting book, book.jpg, 2, 2",        // Normal case with different values
            "'', 999.99, 10, Powerful laptop, laptop.jpg, 1, 1",        // Boundary test: empty name
            "Laptop, abc, 10, Powerful laptop, laptop.jpg, 1, 1",       // Boundary test: invalid price
            "Laptop, 999.99, xyz, Powerful laptop, laptop.jpg, 1, 1",   // Boundary test: invalid quantity
            "Laptop, 999.99, 10, Powerful laptop, laptop.jpg, 5, 1", // Boundary test: invalid category
            "Laptop, 999.99, 10, Powerful laptop, laptop.jpg, 1, 5" // Boundary test: invalid country
    })
    void testAddProduct(String name, String price, String quantity, String description,
                        String image, String category, String country) throws SQLException {

        String sql = "INSERT INTO Product (name,price,quantity,description,image,status,categoryId,countryId,qtysold) VALUES "
                + "(?,?,?,?,?,1,?,?,0)";
        // Define the behavior of connection.prepareStatement(sql)
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        productDAO.addProduct(name, price, quantity, description, image, category, country);

        verify(preparedStatement, times(1)).setString(1, name);
        verify(preparedStatement, times(1)).setString(2, price);
        verify(preparedStatement, times(1)).setString(3, quantity);
        verify(preparedStatement, times(1)).setString(4, description);
        verify(preparedStatement, times(1)).setString(5, image);
        verify(preparedStatement, times(1)).setString(6, category);
        verify(preparedStatement, times(1)).setString(7, country);
        verify(preparedStatement, times(1)).executeUpdate();

        reset(preparedStatement);
    }

    @ParameterizedTest
    @CsvSource({
            "Born To Die, 15, 20, LDR, Lana Del Rey.jpg, 1, 1, 1, 30",  // Normal case
            "Born To Die, 15, 20, LDR, Lana Del Rey.jpg, 1, 1, 1, 40",  // ID doesn't exist
            "Born To Die, 15, 20, LDR, Lana Del Rey.jpg, 1, 1, 1, 29" ,       // Normal case with different values
            ", 15, 20, LDR, Lana Del Rey.jpg, 1, 1, 1, 30" ,       // Boundary test: empty name
            "Born To Die, eee, 20, LDR, Lana Del Rey.jpg, 1, 1, 1, 30" ,      // Boundary test: invalid price
            "Born To Die, 15, eee, LDR, Lana Del Rey.jpg, 1, 1, 1, 30" ,  // Boundary test: invalid quantity
            "Born To Die, 15, 20, LDR, Lana Del Rey.jpg, 1, 10, 1, 30" ,   // Boundary test: invalid category
            "Born To Die, 15, 20, LDR, Lana Del Rey.jpg, 1, 1, 10, 30"     // Boundary test: invalid country
    })
    void testEditProduct(String name, String price, String quantity, String description,
                         String image, String status, String category, String country, String pid) throws SQLException {
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
// Define the behavior of connection.prepareStatement(sql)
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        // Mock the behavior of connection.prepareStatement(sql)
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        // Call the method under test
        productDAO.editProduct(name, price, quantity, description, image, status, category, country, pid);

        // Verify that setString and executeUpdate were called exactly once
        verify(preparedStatement, times(1)).setString(1, name);
        verify(preparedStatement, times(1)).setString(2, price);
        verify(preparedStatement, times(1)).setString(3, quantity);
        verify(preparedStatement, times(1)).setString(4, description);
        verify(preparedStatement, times(1)).setString(5, image);
        verify(preparedStatement, times(1)).setString(6, status);
        verify(preparedStatement, times(1)).setString(7, category);
        verify(preparedStatement, times(1)).setString(8, country);
        verify(preparedStatement, times(1)).setString(9, pid);
        verify(preparedStatement, times(1)).executeUpdate();

        // Reset mock interactions for the next test case
        reset(preparedStatement);
    }

    @ParameterizedTest
    @CsvSource({
            "28, Honeymoon, 19.99, 10, Lana Del Rey - Honeymoon, testimage.jpg, true, 1, 1, 0",
            "30, Born To Die, 12, 12, Lana Del Rey, abc.jpeg, true, 2, 1, 0",
            "50, Born To Die, 12, 12, Lana Del Rey, abc.jpeg, true, 2, 1, 0"
    })
    void testFindProductById(String pid, String name, float price, int quantity, String description,
                             String image, boolean status, int categoryId, int countryId, int qtysold) throws SQLException {
        String sql = "select * from Product where id = ?;";
        Category mockCategory = mock(Category.class);
        Country mockCountry = mock(Country.class);

        // Mock the behavior of the connection to return the prepared statement
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        // Mock the behavior of the prepared statement to return the result set
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Mock the behavior of the result set to return a row
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(Integer.parseInt(pid));
        when(resultSet.getString("name")).thenReturn(name);
        when(resultSet.getFloat("price")).thenReturn(price);
        when(resultSet.getInt("quantity")).thenReturn(quantity);
        when(resultSet.getString("description")).thenReturn(description);
        when(resultSet.getString("image")).thenReturn(image);
        when(resultSet.getBoolean("status")).thenReturn(status);
        when(resultSet.getInt("categoryId")).thenReturn(categoryId);
        when(resultSet.getInt("countryId")).thenReturn(countryId);
        when(resultSet.getInt("additionalField")).thenReturn(qtysold);

        // Mock the behavior of CategoryDAO and CountryDAO
        when(cd.findCategoryById(categoryId)).thenReturn(mockCategory);
        when(ctd.getCountryById(countryId)).thenReturn(mockCountry);

        // Call the method under test
        Product product = productDAO.findProductById(pid);

        // Verify the interactions with the mocks
        verify(connection, times(1)).prepareStatement(sql);
        verify(preparedStatement, times(1)).setString(1, pid);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(1)).next();

        // Validate the result
        assertNotNull(product);
        assertEquals(Integer.parseInt(pid), product.getId());
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(quantity, product.getQuantity());
        assertEquals(description, product.getDescription());
        assertEquals(image, product.getImage());
        assertEquals(status, product.isStatus());
        assertEquals(mockCategory, product.getCategory());
        assertEquals(mockCountry, product.getCountry());
        assertEquals(qtysold, product.getQtysold());

        // Reset the mocks for the next test case
        reset(connection, preparedStatement, resultSet, cd, ctd);
    }

    @ParameterizedTest
    @CsvSource({
            "28, Honeymoon, 19.99, 10, Lana Del Rey - Honeymoon, testimage.jpg, true, 1, 1, 0",
            "30, Born To Die, 12, 12, Lana Del Rey, abc.jpeg, true, 2, 1, 0",
            "50, Born To Die, 12, 12, Lana Del Rey, abc.jpeg, true, 2, 1, 0"
    })
    void testGetNewArrivalProduct(int id, String name, float price, int quantity, String description,
                                  String image, boolean status, int categoryId, int countryId, int qtysold) throws SQLException {
        String sql = "SELECT * FROM Product ORDER BY id DESC LIMIT 10;";
        Category mockCategory = mock(Category.class);
        Country mockCountry = mock(Country.class);

        // Mock the behavior of the connection to return the prepared statement
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        // Mock the behavior of the prepared statement to return the result set
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Mock the behavior of the result set to return a row with provided test data
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(id);
        when(resultSet.getString("name")).thenReturn(name);
        when(resultSet.getFloat("price")).thenReturn(price);
        when(resultSet.getInt("quantity")).thenReturn(quantity);
        when(resultSet.getString("description")).thenReturn(description);
        when(resultSet.getString("image")).thenReturn(image);
        when(resultSet.getBoolean("status")).thenReturn(status);
        when(resultSet.getInt("categoryId")).thenReturn(categoryId);
        when(resultSet.getInt("countryId")).thenReturn(countryId);
        when(resultSet.getInt("additionalField")).thenReturn(qtysold);

        // Mock the behavior of CategoryDAO and CountryDAO
        when(cd.findCategoryById(categoryId)).thenReturn(mockCategory);
        when(ctd.getCountryById(countryId)).thenReturn(mockCountry);

        // Call the method under test
        List<Product> products = productDAO.getNewArrivalProduct();

        // Verify the interactions with the mocks
        verify(connection, times(1)).prepareStatement(sql);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(4)).next(); // 3 products + 1 for end of ResultSet

        // Validate the result
        assertNotNull(products);
        assertEquals(3, products.size());

        Product product = products.get(0);
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(quantity, product.getQuantity());
        assertEquals(description, product.getDescription());
        assertEquals(image, product.getImage());
        assertEquals(status, product.isStatus());
        assertEquals(mockCategory, product.getCategory());
        assertEquals(mockCountry, product.getCountry());
        assertEquals(qtysold, product.getQtysold());

        // Reset the mocks for the next test case
        reset(connection, preparedStatement, resultSet, cd, ctd);
    }

    @ParameterizedTest
    @CsvSource({
            "28, Honeymoon, 19.99, 10, Lana Del Rey - Honeymoon, testimage.jpg, true, 1, 1, 0",
            "30, Born To Die, 12, 12, Lana Del Rey, abc.jpeg, true, 2, 1, 0",
            "50, Born To Die, 12, 12, Lana Del Rey, abc.jpeg, true, 2, 1, 0"
    })
    void testGetBestSellingProduct(int id, String name, float price, int quantity, String description,
                                   String image, boolean status, int categoryId, int countryId, int qtysold) throws SQLException {
        String sql = "SELECT * FROM Product ORDER BY qtysold DESC LIMIT 10;";
        Category mockCategory = mock(Category.class);
        Country mockCountry = mock(Country.class);

        // Mock the behavior of the connection to return the prepared statement
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        // Mock the behavior of the prepared statement to return the result set
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Mock the behavior of the result set to return a row with provided test data
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(id);
        when(resultSet.getString("name")).thenReturn(name);
        when(resultSet.getFloat("price")).thenReturn(price);
        when(resultSet.getInt("quantity")).thenReturn(quantity);
        when(resultSet.getString("description")).thenReturn(description);
        when(resultSet.getString("image")).thenReturn(image);
        when(resultSet.getBoolean("status")).thenReturn(status);
        when(resultSet.getInt("categoryId")).thenReturn(categoryId);
        when(resultSet.getInt("countryId")).thenReturn(countryId);
        when(resultSet.getInt("qtysold")).thenReturn(qtysold);

        // Mock the behavior of CategoryDAO and CountryDAO
        when(cd.findCategoryById(categoryId)).thenReturn(mockCategory);
        when(ctd.getCountryById(countryId)).thenReturn(mockCountry);

        // Call the method under test
        List<Product> products = productDAO.getBestSellingProduct();

        // Verify the interactions with the mocks
        verify(connection, times(1)).prepareStatement(sql);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(4)).next(); // 3 products + 1 for end of ResultSet

        // Validate the result
        assertNotNull(products);
        assertEquals(3, products.size());

        Product product = products.get(0);
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(quantity, product.getQuantity());
        assertEquals(description, product.getDescription());
        assertEquals(image, product.getImage());
        assertEquals(status, product.isStatus());
        assertEquals(mockCategory, product.getCategory());
        assertEquals(mockCountry, product.getCountry());
        assertEquals(qtysold, product.getQtysold());

        // Reset the mocks for the next test case
        reset(connection, preparedStatement, resultSet, cd, ctd);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 28, Honeymoon, 19.99, 10, Lana Del Rey - Honeymoon, testimage.jpg, true, 1, 1, 0",
            "2, 30, Born To Die, 12, 12, Lana Del Rey, abc.jpeg, true, 2, 1, 0",
            "2, 50, Born To Die, 12, 12, Lana Del Rey, abc.jpeg, true, 2, 1, 0"
    })
    void testGetRelatedProducts(String cid, String pid, String name, float price, int quantity, String description,
                                String image, boolean status, int categoryId, int countryId, int qtysold) throws SQLException {
        String sql = "SELECT * FROM Product WHERE categoryId = ? AND id != ? ORDER BY RAND() LIMIT 4;";

        // Simulate the behavior of Category and Country within the test
        Category mockCategory = mock(Category.class);
        Country mockCountry = mock(Country.class);

        // Mock the behavior of the connection to return the prepared statement
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        // Mock the behavior of the prepared statement to return the result set
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Mock the behavior of the result set to return a row with provided test data
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1, 2, 3);
        when(resultSet.getString("name")).thenReturn(name);
        when(resultSet.getFloat("price")).thenReturn(price);
        when(resultSet.getInt("quantity")).thenReturn(quantity);
        when(resultSet.getString("description")).thenReturn(description);
        when(resultSet.getString("image")).thenReturn(image);
        when(resultSet.getBoolean("status")).thenReturn(status);
        when(resultSet.getInt("categoryId")).thenReturn(categoryId);
        when(resultSet.getInt("countryId")).thenReturn(countryId);
        when(resultSet.getInt("qtysold")).thenReturn(qtysold);

        // Mock the behavior of CategoryDAO and CountryDAO
        when(cd.findCategoryById(categoryId)).thenReturn(mockCategory);
        when(ctd.getCountryById(countryId)).thenReturn(mockCountry);

        // Call the method under test
        List<Product> products = productDAO.getRelatedProducts(cid, pid);

        // Verify the interactions with the mocks
        verify(connection, times(1)).prepareStatement(sql);
        verify(preparedStatement, times(1)).setString(1, cid);
        verify(preparedStatement, times(1)).setString(2, pid);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(4)).next(); // 3 products + 1 for end of ResultSet

        // Validate the result
        assertNotNull(products);
        assertEquals(3, products.size());

        for (Product product : products) {
            assertNotNull(product);
            assertEquals(name, product.getName());
            assertEquals(price, product.getPrice());
            assertEquals(quantity, product.getQuantity());
            assertEquals(description, product.getDescription());
            assertEquals(image, product.getImage());
            assertEquals(status, product.isStatus());
            assertEquals(mockCategory, product.getCategory());
            assertEquals(mockCountry, product.getCountry());
            assertEquals(qtysold, product.getQtysold());
        }

        // Reset the mocks for the next test case
        reset(connection, preparedStatement, resultSet, cd, ctd);
    }

    @ParameterizedTest
    @CsvSource({
            "32, ultraviolence, 12, 12, Lana Del Rey, abc.jpeg, true, 2, 1, 0",
            "31, Born To Die, 12, 12, Lana Del Rey, abc.jpeg, true, 2, 1, 0",
            "30, Born To Die, 12, 12, Lana Del Rey, abc.jpeg, true, 2, 1, 0"
    })
    void testGetLimitedEditionProduct(int id, String name, float price, int quantity, String description,
                                      String image, boolean status, int categoryId, int countryId, int qtysold) throws SQLException {
        String sql = "SELECT * FROM Product WHERE `name` LIKE '%limited edition%' ORDER BY id DESC LIMIT 10;";
        Category mockCategory = mock(Category.class);
        Country mockCountry = mock(Country.class);

        // Mock the behavior of the connection to return the prepared statement
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        // Mock the behavior of the prepared statement to return the result set
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Mock the behavior of the result set to return a row with provided test data
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(id);
        when(resultSet.getString("name")).thenReturn(name);
        when(resultSet.getFloat("price")).thenReturn(price);
        when(resultSet.getInt("quantity")).thenReturn(quantity);
        when(resultSet.getString("description")).thenReturn(description);
        when(resultSet.getString("image")).thenReturn(image);
        when(resultSet.getBoolean("status")).thenReturn(status);
        when(resultSet.getInt("categoryId")).thenReturn(categoryId);
        when(resultSet.getInt("countryId")).thenReturn(countryId);
        when(resultSet.getInt("qtysold")).thenReturn(qtysold);

        // Mock the behavior of CategoryDAO and CountryDAO
        when(cd.findCategoryById(categoryId)).thenReturn(mockCategory);
        when(ctd.getCountryById(countryId)).thenReturn(mockCountry);

        // Call the method under test
        List<Product> products = productDAO.getLimitedEditionProduct();

        // Verify the interactions with the mocks
        verify(connection, times(1)).prepareStatement(sql);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(2)).next(); // 1 product + 1 for end of ResultSet

        // Validate the result
        assertNotNull(products);
        assertEquals(1, products.size());

        Product product = products.get(0);
        assertNotNull(product);
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(quantity, product.getQuantity());
        assertEquals(description, product.getDescription());
        assertEquals(image, product.getImage());
        assertEquals(status, product.isStatus());
        assertEquals(mockCategory, product.getCategory());
        assertEquals(mockCountry, product.getCountry());
        assertEquals(qtysold, product.getQtysold());

        // Reset the mocks for the next test case
        reset(connection, preparedStatement, resultSet, cd, ctd);
    }

    @ParameterizedTest
    @CsvSource({
            "15590",
            "20",
            "30"
    })
    void testGetTotalProduct(int totalQuantity) throws SQLException {
        String sql = "select sum(quantity) from Product;";

        // Mock the behavior of the connection, prepared statement, and result set
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(totalQuantity);

        // Call the method under test
        int result = productDAO.getTotalProduct();

        // Verify the interactions with the mocks
        verify(connection, times(1)).prepareStatement(sql);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(1)).next();
        verify(resultSet, times(1)).getInt(1);

        // Validate the result
        assertEquals(totalQuantity, result);

        // Reset the mocks for the next test case
        reset(connection, preparedStatement, resultSet);
    }
}