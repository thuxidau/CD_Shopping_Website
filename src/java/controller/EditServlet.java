package controller;

import dal.CategoryDAO;
import dal.CountryDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Country;
import model.Product;

@WebServlet(name="EditServlet", urlPatterns={"/edit"})
public class EditServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pid = request.getParameter("pid");
        ProductDAO pd = new ProductDAO();
        Product p = pd.findProductById(pid);
        request.setAttribute("detail", p);
        CategoryDAO d = new CategoryDAO();
        List<Category> c = d.getAll();
        CountryDAO ctd = new CountryDAO();
        List<Country> ct = ctd.getAll();
        
        request.setAttribute("country", ct);
        request.setAttribute("category", c);
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pid = request.getParameter("id");
        String pname = request.getParameter("name");
        String pprice = request.getParameter("price");
        String pquantity = request.getParameter("quantity");
        String pdescription = request.getParameter("description");
        String pimage = request.getParameter("image");
        String pcategory = request.getParameter("category");
        String pcountry = request.getParameter("country");
        String pstatus = request.getParameter("status");
        ProductDAO pd = new ProductDAO();
        pd.editProduct(pname, pprice, pquantity, pdescription, pimage, pstatus, pcategory, pcountry, pid);
        response.sendRedirect("manage");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}