package controller;

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
import model.WishList;
import model.Item;
import model.Product;

@WebServlet(name="AddToWishListServlet", urlPatterns={"/addtowishlist"})
public class AddToWishListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewWishListServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewWishListServlet at " + request.getContextPath () + "</h1>");
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
        ProductDAO pd = new ProductDAO();
        String productid = request.getParameter("pid");
        Product p = pd.findProductById(productid);
        request.setAttribute("detail", p);
        
        HttpSession session = request.getSession();
        WishList wishlist = null;
        Object o = session.getAttribute("wishlist");
        if(o != null){
            wishlist = (WishList) o;
        } else {
            wishlist = new WishList();
        }
        String quantity = request.getParameter("quantity");
        Item item = new Item(p, 1, p.getPrice());
        wishlist.addItem(item);
        
        String cid = String.valueOf(p.getCategory().getId());
        List<Product> list = pd.getRelatedProducts(cid, productid);
        request.setAttribute("related", list);
        
        List<Item> list_item = wishlist.getItems();
        session.setAttribute("wishlist", wishlist);
        session.setAttribute("wlsize", list_item.size());
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}