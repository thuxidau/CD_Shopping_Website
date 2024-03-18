package controller;

import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category;
import model.Users;

@WebServlet(name = "MyAccountServlet", urlPatterns = {"/myaccount"})
public class MyAccountServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyAccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyAccountServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO d = new CategoryDAO();
        List<Category> c = d.getAll();
        request.setAttribute("category", c);
        HttpSession session = request.getSession();
        Users us = (Users) session.getAttribute("account");
        if (us != null) {
            Users u = new Users(us.getUsername(), us.getFullname(), us.getPhoneNumber(),
                    us.getEmail(), us.getGender(), us.getLocation(),
                    us.getPassword(), us.getDob(), us.getRoleId(),
                    us.getImage(), us.getAddress());
            request.setAttribute("user", u);
            request.getRequestDispatcher("myaccount.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
