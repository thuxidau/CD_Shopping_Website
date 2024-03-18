package controller;

import dal.UsersDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

@WebServlet(name="SignUpServlet", urlPatterns={"/signup"})
public class SignUpServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String uName = request.getParameter("username");
        String mail = request.getParameter("email");
        String pass = request.getParameter("password");
        String cfpass = request.getParameter("confirm_password");
        if(!pass.equals(cfpass)){
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        } else {
            UsersDAO ud = new UsersDAO();
            Users u = ud.checkAccountExist(uName);
            if(u == null){
                ud.signUp(uName, pass, mail);
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}