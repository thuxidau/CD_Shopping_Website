package controller;

import dal.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/changepassword"})
public class ChangePasswordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if(session.getAttribute("account") == null){
            response.sendRedirect("login");
        } else {
            String user = request.getParameter("username");
            String oldpass = request.getParameter("oldpassword");
            String newpass = request.getParameter("newpassword");
            String cfnewpass = request.getParameter("cfnewpassword");
            UsersDAO ud = new UsersDAO();
            if (oldpass != null) {
                Users u = ud.check(user, oldpass);
                if (u == null) {
                    request.setAttribute("oldpasserror", "Your old password is incorrect.");
                    request.getRequestDispatcher("changepassword.jsp").forward(request, response);
                } else {
                    if (!newpass.equals(cfnewpass)) {
                        request.setAttribute("cfpasserror", "Passwords do not match.");
                        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
                    } else {
                        Users us = new Users(user, u.getFullname(), u.getPhoneNumber(),
                                u.getEmail(), u.getGender(), u.getLocation(),
                                newpass, u.getDob(), u.getRoleId(), u.getImage(),
                                u.getAddress());
                        ud.updatePassword(us);
                        session.setAttribute("account", us);
                        request.setAttribute("passsuccess", "Password changed successfully.");
                        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
                    }
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
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
