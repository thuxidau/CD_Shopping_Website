package controller;

import dal.OrderDAO;
import dal.OrderDetailDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.OrderDetail;
import model.Users;

@WebServlet(name="CustomerOrdersServlet", urlPatterns={"/customerorders"})
public class CustomerOrdersServlet extends HttpServlet {

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
            OrderDetailDAO odd = new OrderDetailDAO();
            OrderDAO od = new OrderDAO();
            
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int countOrder = odd.getTotalOrders();
            int endPage = countOrder / 15;
            if (countOrder % 15 != 0) {
                endPage++;
            }

            List<OrderDetail> pagingOrder = odd.pagingOrder(index);
            request.setAttribute("order", pagingOrder);
            request.setAttribute("endP", endPage);
            request.setAttribute("tagi", index);
            
            request.getRequestDispatcher("customerorders.jsp").forward(request, response);
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