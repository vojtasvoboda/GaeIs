package servlets;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.Admins;
import persistence.AdminsDAO;
import persistence.Category;
import persistence.CategoryDAO;

/**
 * Servlet pro mazání kategorii
 * @author Bc. Michal Vcelicka
 */
public class AdminsDelete extends HttpServlet{

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
        System.out.println("CategoryDelete: id pro smazání je " + id);
        // zjistime klic
        Key k = KeyFactory.stringToKey(id);
        // zjistime kategorii z DAO dle klice
        AdminsDAO dao = new AdminsDAO();
        Admins adm = dao.get(k);
        System.out.println("CategoryDelete: id z objektu je " + adm.getId());
        // a smazeme ho
        dao.delete(adm);

        resp.sendRedirect("/admins?smazano=1");
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
