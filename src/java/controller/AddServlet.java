package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="AddServlet", urlPatterns={"/add"})
public class AddServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.setCharacterEncoding("UTF-8");
        String pname = request.getParameter("name");
        String pprice = request.getParameter("price");
        String pquantity = request.getParameter("quantity");
        String pdescription = request.getParameter("description");
        String pimage = request.getParameter("image");
        String pcategory = request.getParameter("category");
        String pcountry = request.getParameter("country");
        ProductDAO pd = new ProductDAO();
        pd.addProduct(pname, pprice, pquantity, pdescription, pimage, pcategory, pcountry);
        response.sendRedirect("manage");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}