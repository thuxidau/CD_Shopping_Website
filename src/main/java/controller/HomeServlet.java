package controller;

import dal.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;

@WebServlet(name="HomeServlet", urlPatterns={"/home"})
public class HomeServlet extends HttpServlet {
    public HomeServlet() {
        super();
    }

    private ProductDAO productDAO = new ProductDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        List<Product> p = productDAO.getAll();
        request.setAttribute("product", p);
        
        List<Product> bestselling = productDAO.getBestSellingProduct();
        request.setAttribute("bestsell", bestselling);
        
        List<Product> list = productDAO.getLimitedEditionProduct();
        request.setAttribute("trend", list);
        
        List<Product> na = productDAO.getNewArrivalProduct();
        request.setAttribute("newa", na);
        
        List<Product> usuk = productDAO.getTop5US_UK();
        request.setAttribute("usuk", usuk);
        
        List<Product> kpop = productDAO.getTop5K_POP();
        request.setAttribute("kpop", kpop);
        
        List<Product> vpop = productDAO.getTop5V_POP();
        request.setAttribute("vpop", vpop);
        
        request.getRequestDispatcher("home.jsp").forward(request, response);
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