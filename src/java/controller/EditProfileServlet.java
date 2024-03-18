package controller;

import dal.UsersDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Path;
import model.Users;

@MultipartConfig
@WebServlet(name="EditProfileServlet", urlPatterns={"/editprofile"})
public class EditProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("account");
        if(user == null){
            response.sendRedirect("login");
        } else {
            String username = request.getParameter("username");
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String g = request.getParameter("gender");
            int gender = Integer.parseInt(g);
            String location = request.getParameter("location");
            String dob = request.getParameter("dob");
            String address = request.getParameter("address");
            

            UsersDAO ud = new UsersDAO();
            ud.editUser(fullname, phone, email, gender, location, dob, address, username);
            Users uuu = ud.checkAccountExist(username);
            session.setAttribute("account", uuu);
            request.setAttribute("msg", "Updated successfully!");
            request.getRequestDispatcher("myaccount.jsp").forward(request, response);
        }
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