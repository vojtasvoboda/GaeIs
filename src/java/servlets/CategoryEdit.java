package servlets;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.Category;
import persistence.CategoryDAO;

/**
 * Servlet pro pridavani dodavatelu
 * @author Bc. Michal Vcelicka
 */
public class CategoryEdit extends HttpServlet{

    /**
     * Obslouzi data prichazejici POSTem
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

        String caption = checkNull(req.getParameter("caption"));
        String id_cat = checkNull(req.getParameter("id_category"));
        // musime vytvorit klic
        Key k = KeyFactory.stringToKey(id_cat);
        // najdeme objekt
        CategoryDAO catdao = new CategoryDAO();
        Category cat = catdao.get(k);
        // upravime polozky na novy
        cat.setCaption(caption);
        // a ulozime
        catdao.save(cat);
        catdao.closePM();
        resp.sendRedirect("/categories?upraveno=1");
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
