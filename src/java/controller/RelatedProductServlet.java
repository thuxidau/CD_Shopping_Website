package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;

@WebServlet(name="RelatedProductServlet", urlPatterns={"/related"})
public class RelatedProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RelatedProductServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RelatedProductServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ProductDAO pd = new ProductDAO();
        
        String catid = request.getParameter("pid");
        Product p = pd.findProductById(catid);
        
        int cid = p.getCategory().getId();
        String c_id = String.valueOf(cid);
        int pid = p.getId();
        String p_id = String.valueOf(pid);
        
        List<Product> list = pd.getRelatedProducts(c_id, p_id);
        request.setAttribute("related", list);
        request.getRequestDispatcher("relatedproduct.jsp").forward(request, response);
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