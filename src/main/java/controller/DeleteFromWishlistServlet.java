package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Item;
import model.WishList;

@WebServlet(name="DeleteFromWishlistServlet", urlPatterns={"/delfromwl"})
public class DeleteFromWishlistServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        WishList wishlist = null;
        Object w = session.getAttribute("wishlist");
        if (w != null) {
            wishlist = (WishList) w;
        } else {
            wishlist = new WishList();
        }
        String pid = request.getParameter("pid");
        int id = Integer.parseInt(pid);
        wishlist.removeItem(id);
        session.setAttribute("wishlist", wishlist);
        
        List<Item> list_item = wishlist.getItems();
        session.setAttribute("wlsize", list_item.size());
        request.getRequestDispatcher("wishlist.jsp").forward(request, response);
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