package servlets;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
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
public class OrderEdit extends HttpServlet{

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
        String id_order = checkNull(req.getParameter("id_order"));
        String date = checkNull(req.getParameter("date"));
        String price = checkNull(req.getParameter("price"));
        String pozn = checkNull(req.getParameter("pozn"));

        // pretypujeme parametry
        int suppl = Integer.valueOf(id_supplier);
        int pricen = Integer.valueOf(price);

        // vytvorime a ulozime novou objednavku
         // musime vytvorit klic
        Key k = KeyFactory.stringToKey(id_order);

        // najdeme objekt
        OrderxDAO ordDAO = new OrderxDAO();
        Orderx ord = ordDAO.get(k);

        // upravime polozky na novy
        ord.setAmount(pricen);
        ord.setDate_ordered(date);
        ord.setId_supplier(suppl);
        ord.setNote(pozn);
        // a ulozime
        ordDAO.save(ord);
        ordDAO.closePM();

        resp.sendRedirect("/orders?upraveno=1");
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
