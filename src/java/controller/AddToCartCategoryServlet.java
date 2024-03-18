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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.Category;
import model.Item;
import model.Product;

@WebServlet(name="AddToCartCategoryServlet", urlPatterns={"/addtocartcategory"})
public class AddToCartCategoryServlet extends HttpServlet {

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
        
        String categoryID = request.getParameter("cid");
        
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
        request.getRequestDispatcher("category.jsp").forward(request, response);
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