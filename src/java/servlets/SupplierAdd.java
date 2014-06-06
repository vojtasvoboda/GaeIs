package servlets;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.Supplier;
import persistence.SupplierDAO;

/**
 * Servlet pro pridavani dodavatelu
 * @author Bc. Michal Vcelicka
 */
public class SupplierAdd extends HttpServlet{

    /**
     * Obslouzi data prichazejici POSTem
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

        String name = checkNull(req.getParameter("Name"));
        String ico = checkNull(req.getParameter("ICO"));
        String dic = checkNull(req.getParameter("DIC"));
        String address = checkNull(req.getParameter("Address"));
        String contact_person = checkNull(req.getParameter("Contact_person"));

        SupplierDAO dao = new SupplierDAO();
        Supplier supp = new Supplier(ico, dic, address, name, contact_person);
        dao.save(supp);

        resp.sendRedirect("/suppliers?vlozeno=1");
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
