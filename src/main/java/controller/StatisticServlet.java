package controller;

import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.ProductDAO;
import dal.TopSpendingDAO;
import dal.UsersDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Product;
import model.TopSpending;
import model.Users;

@WebServlet(name="StatisticServlet", urlPatterns={"/statistic"})
public class StatisticServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Users u = (Users) session.getAttribute("account");
        if(session.getAttribute("account") == null || u.getRoleId() != 0){
            response.sendRedirect("login");
        } else {
            ProductDAO pd = new ProductDAO();
            OrderDAO od = new OrderDAO();
            OrderDetailDAO odd = new OrderDetailDAO();
            UsersDAO ud = new UsersDAO();
            TopSpendingDAO tsd = new TopSpendingDAO();

            List<Product> listP = pd.getAll();
            int listP_size = listP.size();
            request.setAttribute("totalproduct", listP_size);

            int instock = pd.getTotalProduct();
            request.setAttribute("instock", instock);

            int qtysold = pd.getTotalProductSold();
            request.setAttribute("sold", qtysold);

            float earn = od.getEarn();
            request.setAttribute("earn", earn);

            int orders = odd.getTotalOrders();
            request.setAttribute("orders", orders);

            List<Users> listU = ud.getAll();
            request.setAttribute("user", listU.size());

            List<Product> bestselling = pd.getBestSellingProduct();
            request.setAttribute("bestsell", bestselling);

            List<TopSpending> mostspending = tsd.getTop5Spending();
            request.setAttribute("mostspend", mostspending);
            request.getRequestDispatcher("statistic.jsp").forward(request, response);
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}