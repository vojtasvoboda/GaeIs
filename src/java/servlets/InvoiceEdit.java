package servlets;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.Invoice;
import persistence.InvoiceDAO;
import persistence.Supplier;
import persistence.SupplierDAO;

/**
 * Servlet pro pridavani dodavatelu
 * @author Bc. Michal Vcelicka
 */
public class InvoiceEdit extends HttpServlet{

    /**
     * Obslouzi data prichazejici POSTem
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

        String id_invoice = checkNull(req.getParameter("id_invoice"));
        String date = checkNull(req.getParameter("date"));
        String orderID = checkNull(req.getParameter("id_order"));

        // musime vytvorit klic
        Key k = KeyFactory.stringToKey(id_invoice);

        // najdeme objekt
        InvoiceDAO invDao = new InvoiceDAO();
        Invoice inv = invDao.get(k);

        // upravime polozky na novy
        inv.setDate_to_by_paid(date);
        inv.setId_order(Integer.valueOf(orderID));

        // a ulozime
        invDao.save(inv);
        invDao.closePM();

        resp.sendRedirect("/invoices?upraveno=1");
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
