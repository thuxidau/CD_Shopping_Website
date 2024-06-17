package controller;

import dal.UsersDAO;
import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

@WebServlet(name="SignUpServlet", urlPatterns={"/signup"})
public class SignUpServlet extends HttpServlet {

    private UsersDAO usersDAO;

    public SignUpServlet() {
        this.usersDAO = new UsersDAO(); // Default constructor for normal use
    }

    // Constructor for testing
    public SignUpServlet(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String uName = request.getParameter("username");
        String mail = request.getParameter("email");
        String pass = request.getParameter("password");
        String cfpass = request.getParameter("confirm_password");

        if (uName.length() > 50) {
            request.setAttribute("error", "Username cannot exceed 50 characters");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        if (pass.length() > 100) {
            request.setAttribute("error", "Password cannot exceed 100 characters");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        if(!pass.equals(cfpass)){
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        } else {
            Users u = usersDAO.checkAccountExist(uName);
            if(u == null){
                usersDAO.signUp(uName, pass, mail);
                response.sendRedirect("login");
            } else {
                request.setAttribute("userer", "This username already existed");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}