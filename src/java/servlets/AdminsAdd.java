package servlets;

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
public class AdminsAdd extends HttpServlet{

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
        String user_id = checkNull(req.getParameter("id_user"));

        AdminsDAO admDao = new AdminsDAO();
        Admins adm = new Admins(user_name,real_name,role);

        admDao.save(adm);
        resp.sendRedirect("/admins?vlozeno=1");
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
