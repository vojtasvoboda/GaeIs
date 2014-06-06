package servlets;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
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
public class SupplierEdit extends HttpServlet{

    /**
     * Obslouzi data prichazejici POSTem
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

        String id_supplier = checkNull(req.getParameter("id_supplier"));
        System.out.printf("SupplierEdit ID_SUPPLIER - "  + id_supplier);
        String address = checkNull(req.getParameter("Address"));
        String name = checkNull(req.getParameter("Name"));
        String ico = checkNull(req.getParameter("ICO"));
        String dic = checkNull(req.getParameter("DIC"));
        String contact = checkNull(req.getParameter("Contact_person"));

        // musime vytvorit klic
        Key k = KeyFactory.stringToKey(id_supplier);

        // najdeme objekt
        SupplierDAO catdao = new SupplierDAO();
        Supplier cat = catdao.get(k);

        // upravime polozky na novy
        cat.setAddress(address);
        cat.setName(name);
        cat.setIco(ico);
        cat.setDic(dic);
        cat.setContact(contact);

        // a ulozime
        catdao.save(cat);
        catdao.closePM();

        resp.sendRedirect("/suppliers?upraveno=1");
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
