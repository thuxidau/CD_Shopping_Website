package controller;

import dal.UsersDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "DelAccServlet", urlPatterns = {"/delacc"})
public class DelAccServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String uu = request.getParameter("user");
        UsersDAO ud = new UsersDAO();
        ud.removeAccount(uu);

        HttpSession session = request.getSession();
        if (session.getAttribute("account") != null) {
            session.removeAttribute("account");
        }

        Cookie u = new Cookie("userC", "");
        Cookie p = new Cookie("passC", "");
        
        u.setMaxAge(0);
        p.setMaxAge(0);

        response.addCookie(u);
        response.addCookie(p);
        response.sendRedirect("home");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
