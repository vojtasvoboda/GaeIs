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
 * Servlet pro mazání dodavatelu
 * @author Bc. Vojtěch Svoboda
 */
public class OrderDelete extends HttpServlet{

    /**
     * Obslouzi data prichazejici GETem
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

        // ziskame id pro smazani
        String id = checkNull(req.getParameter("id"));
        System.out.println("OrderDelete: id pro smazání je " + id);
        // zjistime klic
        Key k = KeyFactory.stringToKey(id);
        // zjistime kategorii z DAO dle klice
        OrderxDAO dao = new OrderxDAO();
        Orderx supp = dao.get(k);
        System.out.println("OrderDelete: id z objektu je " + supp.getId());
        // a smazeme ho
        dao.delete(supp);

        resp.sendRedirect("/orders?smazano=1");
    }

    /**
     * Osetreni nulovych parametru
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
