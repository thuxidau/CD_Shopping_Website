package controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import dal.UsersDAO;
import model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

class LoginServletTest {

    @InjectMocks
    private LoginServlet loginServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private UsersDAO usersDAO;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Captor
    private ArgumentCaptor<Users> userCaptor;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    // Null username and password
    @Test
    void testDoPost_InvalidUser() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("invalidUser");
        when(request.getParameter("password")).thenReturn("invalidPass");
        when(usersDAO.check("invalidUser", "invalidPass")).thenReturn(null);
        when(request.getRequestDispatcher("login.jsp")).thenReturn(requestDispatcher);

        loginServlet.doPost(request, response);

        verify(request).setAttribute("error", "Username or password invalid!");
        verify(requestDispatcher).forward(request, response);
    }

    // Wrong username and password
    @Test
    void testDoPost_InvalidUsername_InvalidPassword() throws ServletException, IOException {
        String validUsername = "dangtestdunghoi";
        String invalidPassword = "hoigi";

        when(request.getParameter("username")).thenReturn(validUsername);
        when(request.getParameter("password")).thenReturn(invalidPassword);
        when(usersDAO.check(validUsername, invalidPassword)).thenReturn(null);
        when(request.getRequestDispatcher("login.jsp")).thenReturn(requestDispatcher);

        loginServlet.doPost(request, response);

        verify(request).setAttribute("error", "Username or password invalid!");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoPost_ValidUser_WithRememberMe() throws ServletException, IOException {
        String username = "thuxidau";
        String password = "123321";
        String rememberMe = "on";

        Users mockUser = new Users(
                "thuxidau", "Thu Doan", "0305070901", "thudoan@fpt.edu.vn", 1, "Hanoi",
                "123321", "2004-09-02", 0, "./img/avatar/myavt.jpg", "10 Khuong Ha, Khuong Dinh, Thanh Xuan"
        );

        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("rememberMe")).thenReturn(rememberMe);
        when(usersDAO.check(username, password)).thenReturn(mockUser);
        when(request.getSession()).thenReturn(session);

        loginServlet.doPost(request, response);

        verify(session).setAttribute(eq("account"), userCaptor.capture());
        Users capturedUser = userCaptor.getValue();

        assertEquals("thuxidau", capturedUser.getUsername());
        assertEquals("Thu Doan", capturedUser.getFullname());
        assertEquals("0305070901", capturedUser.getPhoneNumber());
        assertEquals("thudoan@fpt.edu.vn", capturedUser.getEmail());
        assertEquals(1, capturedUser.getGender());
        assertEquals("Hanoi", capturedUser.getLocation());
        assertEquals("123321", capturedUser.getPassword());
        assertEquals("2004-09-02", capturedUser.getDob());
        assertEquals(0, capturedUser.getRoleId());
        assertEquals("./img/avatar/myavt.jpg", capturedUser.getImage());
        assertEquals("10 Khuong Ha, Khuong Dinh, Thanh Xuan", capturedUser.getAddress());

        verify(response).addCookie(argThat(cookie -> cookie.getName().equals("userC") && cookie.getValue().equals(username)));
        verify(response).addCookie(argThat(cookie -> cookie.getName().equals("passC") && cookie.getValue().equals(password) && cookie.getMaxAge() == 864000));

        verify(response).sendRedirect("home");
    }

    @Test
    void testDoPost_ValidUser_WithoutRememberMe() throws ServletException, IOException {
        String username = "thuxidau";
        String password = "123321";

        Users mockUser = new Users(
                "thuxidau", "Thu Doan", "0305070901", "thudoan@fpt.edu.vn", 1, "Hanoi",
                "123321", "2004-09-02", 0, "./img/avatar/myavt.jpg", "10 Khuong Ha, Khuong Dinh, Thanh Xuan"
        );

        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("rememberMe")).thenReturn(null);
        when(usersDAO.check(username, password)).thenReturn(mockUser);
        when(request.getSession()).thenReturn(session);

        loginServlet.doPost(request, response);

        verify(session).setAttribute(eq("account"), userCaptor.capture());
        Users capturedUser = userCaptor.getValue();

        assertEquals("thuxidau", capturedUser.getUsername());
        assertEquals("Thu Doan", capturedUser.getFullname());
        assertEquals("0305070901", capturedUser.getPhoneNumber());
        assertEquals("thudoan@fpt.edu.vn", capturedUser.getEmail());
        assertEquals(1, capturedUser.getGender());
        assertEquals("Hanoi", capturedUser.getLocation());
        assertEquals("123321", capturedUser.getPassword());
        assertEquals("2004-09-02", capturedUser.getDob());
        assertEquals(0, capturedUser.getRoleId());
        assertEquals("./img/avatar/myavt.jpg", capturedUser.getImage());
        assertEquals("10 Khuong Ha, Khuong Dinh, Thanh Xuan", capturedUser.getAddress());

        verify(response).addCookie(argThat(cookie -> cookie.getName().equals("userC") && cookie.getValue().equals(username)));
        verify(response).addCookie(argThat(cookie -> cookie.getName().equals("passC") && cookie.getValue().equals(password) && cookie.getMaxAge() == 0));

        verify(response).sendRedirect("home");
    }
}