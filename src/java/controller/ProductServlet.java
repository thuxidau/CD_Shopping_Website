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

@WebServlet(name = "ProductDetailServlet", urlPatterns = {"/detail"})
public class ProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductDAO pd = new ProductDAO();

        String productid = request.getParameter("pid");
        Product p = pd.findProductById(productid);
        

        String cid = String.valueOf(p.getCategory().getId());
//        String productid = String.valueOf(p.getId());
//        String cid = request.getParameter("category");
//        if (cid == null && p != null && p.getCategory() != null) {
//            cid = String.valueOf(p.getCategory().getId());
//        }

        List<Product> list = pd.getRelatedProducts(cid, productid);
        request.setAttribute("detail", p);
        request.setAttribute("related", list);
        request.getRequestDispatcher("product.jsp").forward(request, response);
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
