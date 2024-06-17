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
import model.Cart;
import model.Item;
import model.Product;

@WebServlet(name="DeleteFromCartServlet", urlPatterns={"/del"})
public class DeleteFromCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //        increase / decrease number of product in cart
        HttpSession session = request.getSession();
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String n = request.getParameter("num");
        String p_id = request.getParameter("pid");
        int pr_id, num;
        try {
            pr_id = Integer.parseInt(p_id);
            num = Integer.parseInt(n);
//          neu quantity = 1 ma ngta an '-' thi phai delete from cart
            if (num == -1 && cart.getQuantityById(pr_id) <= 1) {
                cart.removeItem(pr_id);
            } else {
                ProductDAO pd = new ProductDAO();
                Product product = pd.findProductById(p_id);
                float price = product.getPrice();
                Item item = new Item(product, num, price);
                cart.addItem(item);
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        List<Item> list_item = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", list_item.size());
        request.getRequestDispatcher("cart.jsp").forward(request, response);
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