package controller;

import dal.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;

@WebServlet(name="AddToCartInHomeServlet", urlPatterns={"/addtocarthome"})
public class AddToCartInHomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductDAO pd = new ProductDAO();
        String catid = request.getParameter("pid");
        Product p = pd.findProductById(catid);
        request.setAttribute("detail", p);
        
        HttpSession session = request.getSession();
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if(o != null){
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String quantity = request.getParameter("quantity");
        String pr = request.getParameter("price");
        float price = Float.parseFloat(pr);
        String pid = request.getParameter("pid");
        int qty = Integer.parseInt(quantity);
        Product product = pd.findProductById(pid);
        Item item = new Item(product, qty, price);
        cart.addItem(item);
        
        List<Item> list_item = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", list_item.size());
        
        List<Product> pp = pd.getAll();
        request.setAttribute("product", pp);
        List<Product> list = pd.getLimitedEditionProduct();
        request.setAttribute("trend", list);
        List<Product> na = pd.getNewArrivalProduct();
        request.setAttribute("newa", na);
        List<Product> bestselling = pd.getBestSellingProduct();
        request.setAttribute("bestsell", bestselling);
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