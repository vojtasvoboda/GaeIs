package servlets;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.Item;
import persistence.ItemDAO;

/**
 * Servlet pro mazání polozek
 * @author Bc. Vojtěch Svoboda
 */
public class ItemDelete extends HttpServlet{

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
        System.out.println("ItemDelete: id pro smazání je " + id);
        // zjistime klic
        Key k = KeyFactory.stringToKey(id);
        // zjistime kategorii z DAO dle klice
        ItemDAO dao = new ItemDAO();
        Item supp = dao.get(k);
        System.out.println("ItemDelete: id z objektu je " + supp.getId());
        // a smazeme ho
        dao.delete(supp);

        resp.sendRedirect("/items?smazano=1");
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
