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
 * Servlet pro pridavani dodavatelu
 * @author Bc. Michal Vcelicka
 */
public class AdminsEdit extends HttpServlet{

    /**
     * Obslouzi data prichazejici POSTem
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

        String real_name = checkNull(req.getParameter("real_name"));
        String user_name = checkNull(req.getParameter("user_name"));
        String role = checkNull(req.getParameter("role"));
        String user_id = checkNull(req.getParameter("id_admins"));

        Key k = KeyFactory.stringToKey(user_id);
        // najdeme objekt
        AdminsDAO admdao = new AdminsDAO();
        Admins adm = admdao.get(k);
        // upravime polozky na novy
        adm.setReal_name(real_name);
        adm.setRole(role);
        adm.setUser_name(user_name);
        // a ulozime
        admdao.save(adm);
        admdao.closePM();
        resp.sendRedirect("/admins?upraveno=1");
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
