package controller;

import dal.CategoryDAO;
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
import model.Product;

@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CategoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryID = request.getParameter("cid");

        ProductDAO pd = new ProductDAO();
        
        CategoryDAO cd = new CategoryDAO();
        List<Category> getAllCategory = cd.getAll();
        request.setAttribute("category", getAllCategory);

        if (categoryID == null || categoryID.isEmpty()) {
            List<Product> getAllProduct = pd.getAll();
            request.setAttribute("product", getAllProduct);
        } else {
            List<Product> getProductByID = pd.getProductById(categoryID);
            request.setAttribute("product", getProductByID);
        }
        request.setAttribute("tag", categoryID);

//        String indexPage = request.getParameter("index");
//        if (indexPage == null) {
//            indexPage = "1";
//        }
//        int index = Integer.parseInt(indexPage);
//        int countProduct = pd.totalProductCount();
//        int endPage = countProduct / 4;
//        if (countProduct % 4 != 0) {
//            endPage++;
//        }
//
//        List<Product> pagingproduct = pd.pagingProduct(index);
//        request.setAttribute("product", pagingproduct);
//        request.setAttribute("endP", endPage);
//        request.setAttribute("tagi", index);

        request.getRequestDispatcher("category.jsp").forward(request, response);
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
