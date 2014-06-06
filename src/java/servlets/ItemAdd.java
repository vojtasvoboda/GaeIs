package servlets;

import java.util.Date;
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
public class ItemAdd extends HttpServlet{

    /**
     * Obslouzi data prichazejici POSTem
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

        String category = checkNull(req.getParameter("category"));
        String caption = checkNull(req.getParameter("caption"));
        String manufacturer = checkNull(req.getParameter("manufacturer"));
        String pn = checkNull(req.getParameter("pn"));
        String sn = checkNull(req.getParameter("sn"));
        String price = checkNull(req.getParameter("price"));
        String note = checkNull(req.getParameter("pozn"));

        int id_category = Integer.parseInt(category);
        int pricei = Integer.parseInt(price);

        ItemDAO dao = new ItemDAO();
        Item it = new Item(0, id_category, manufacturer, caption, pn, sn, pricei, new Date(), new Date(), 0, 0, note, "");
        dao.save(it);
        resp.sendRedirect("/items?vlozeno=1");
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
