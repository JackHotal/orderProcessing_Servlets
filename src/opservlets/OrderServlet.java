package opservlets;

import oplib.Order;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderServlet" , urlPatterns = {"/Order"},
        initParams = {  @WebInitParam(name = "uid", value = "ism6236"),
                @WebInitParam(name = "pass", value = "ism6236bo")}
)
public class OrderServlet extends HttpServlet {
    Order mdb;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String uid = config.getInitParameter("uid");
        String pass = config.getInitParameter("pass");

        mdb = new Order(uid,pass);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String cid = request.getParameter("cid");
        String quantity = request.getParameter("quantity");
        String od = request.getParameter("orderlist");

        int inx = od.lastIndexOf(",");
        String temp = od.substring(0, inx-1);
        String pd = String.format("%s,%s",temp,quantity);

        List<String> l = new ArrayList<>();
        l.add(pd);
        int n = mdb.Purchase(cid, l);

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Order </title>");
            out.println("</head>");
            out.println("<body>");
            if (n == 3)
                out.println("Produtct Ordered<br>");
            else
                out.println("Order was not sucessful<br>");

            out.println("<a href=\"index.jsp\"> Main Menu </a> <br>");
            out.println("</body>");
            out.println("</html>");
        }

    }


}
