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
 * Servlet pro pridavani polozek
 * @author Bc. Michal Vcelicka
 */
public class ItemEdit extends HttpServlet{

    /**
     * Obslouzi data prichazejici POSTem
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

        String id_item = checkNull(req.getParameter("id_item"));
        String category = checkNull(req.getParameter("category"));
        String caption = checkNull(req.getParameter("caption"));
        String manufacturer = checkNull(req.getParameter("manufacturer"));
        String pn = checkNull(req.getParameter("pn"));
        String sn = checkNull(req.getParameter("sn"));
        String price = checkNull(req.getParameter("price"));
        String note = checkNull(req.getParameter("pozn"));

        int id_category = Integer.parseInt(category);
        int pricei = Integer.parseInt(price);

         // musime vytvorit klic
        Key k = KeyFactory.stringToKey(id_item);

        // najdeme objekt
        ItemDAO itmDao = new ItemDAO();
        Item itn = itmDao.get(k);

        // upravime polozky na novy
        itn.setCaption(caption);
        itn.setId_category(id_category);
        itn.setManufacturer(manufacturer);
        itn.setPn(pn);
        itn.setSn(sn);
        itn.setPrice(pricei);
        itn.setNote(note);
        // a ulozime
        itmDao.save(itn);
        itmDao.closePM();

        resp.sendRedirect("/items?upraveno=1");
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
