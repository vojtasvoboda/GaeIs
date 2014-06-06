package servlets;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.Orderx;
import persistence.OrderxDAO;

/**
 * Servlet pro pridavani objednavek
 * @author Bc. Vojtěch Svoboda
 */
public class OrderAdd extends HttpServlet{

    /**
     * Obslouzi data prichazejici POSTem
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

        // TODO kontrola vyplneni

        // ziskame parametry
        String id_supplier = checkNull(req.getParameter("id_supplier"));
        String date = checkNull(req.getParameter("date"));
        String price = checkNull(req.getParameter("price"));
        String pozn = checkNull(req.getParameter("pozn"));

        // pretypujeme parametry
        System.out.println("OrderAdd: id_supplier je " + id_supplier);
        System.out.println("OrderAdd: price je " + price);
        int suppl = Integer.valueOf(id_supplier);
        int pricen = Integer.valueOf(price);

        // vytvorime a ulozime novou objednavku
        OrderxDAO dao = new OrderxDAO();
        Orderx ord = new Orderx(suppl, date, pricen, pozn);
        dao.save(ord);

        resp.sendRedirect("/orders?vlozeno=1");
    }

    /**
     *
     * @param s
     * @return
     */
    private String checkNull(String s) {
            if (s == null) {
                    return "";
            }
            return s;
    }
}
