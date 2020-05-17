package opservlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import oplib.Order;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/Customer"},
        initParams = {  @WebInitParam(name = "uid", value = "ism6236"),
                        @WebInitParam(name = "pass", value = "ism6236bo")}

)
public class CustomerServlet extends HttpServlet {

    private Order mdb;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);


        String uid = config.getInitParameter("uid");
        String pass = config.getInitParameter("pass");
        mdb = new Order(uid,pass);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String headerstuff = "<meta charset=\"UTF-8\">\n " +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "          <link rel=\"stylesheet\" type =\"text/css\" href =\"servlet.css\" /> ";

        String cid = request.getParameter("cid");

        String cname = mdb.getCustomer(cid);

        List<String> oids = mdb.getCustomerOrders(cid);

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Customer</title>");
            out.println(headerstuff);
            out.println("</head>");
            out.println("<body>");

            out.println("<form ACTION=\"Customer\" METHOD=\"GET\"> ");
            out.println("<fieldset id=\"info\">");
            out.println("<legend > Customer: </legend>");
            String line = String.format("<label for=\"no\"> Customer No: </label> <INPUT id= \"no\" type=text size=20 name=\"cid\" value=\"%s\"> <br>",cid);
            out.println(line);
            line = String.format("<label for=\"name\"> Customer Name: </label> <INPUT id= \"name\" type=text size=20 name=\"cname\" disabled = \"disabled\" value=\"%s\"> <br>  ",cname);
            out.println(line);
            out.println("<label for=\"orderlist\"> Orders: </label> <br> ");
            out.println("<select id = \"orders\" name =\"orderlist\" size=\"10\"> ");
            for (String o : oids)
            {
                List<String> ods = mdb.getOrderDetails(o);
                for (String od: ods ){
                    line = String.format("<option> %s,%s </option>",o,od);
                    out.println(line);
                }
            }

            out.println("</select> <br>");
            out.println(" <INPUT TYPE=\"submit\" VALUE=\"Get Customer\"> ");
            out.println(" <Input TYPE =\"submit\" formaction=\"index.jsp\" value=\"Main Menu\">");
            out.println("</fieldset>");
            out.println("</form>");

            out.println("</body>");
            out.println("</html>");
        }


    }
}
