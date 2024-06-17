package controller;

import dal.CategoryDAO;
import dal.CountryDAO;
import dal.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category;
import model.Country;
import model.Product;
import model.Users;

@WebServlet(name="ManageProductServlet", urlPatterns={"/manage"})
public class ManageProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users u = (Users) session.getAttribute("account");
        if(session.getAttribute("account") == null || u.getRoleId() != 0){
            response.sendRedirect("login");
        } else {
            ProductDAO pd = new ProductDAO();
            List<Product> p = pd.getAll();
            CategoryDAO d = new CategoryDAO();
            List<Category> c = d.getAll();
            CountryDAO ctd = new CountryDAO();
            List<Country> ct = ctd.getAll();
            
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int countProduct = pd.totalProductCount();
            int endPage = countProduct / 10;
            if (countProduct % 10 != 0) {
                endPage++;
            }

            List<Product> pagingproduct = pd.pagingProduct(index);
            request.setAttribute("product", pagingproduct);
            request.setAttribute("endP", endPage);
            request.setAttribute("tagi", index);

            request.setAttribute("country", ct);
            request.setAttribute("category", c);
            request.getRequestDispatcher("manageproduct.jsp").forward(request, response);
        }
        
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