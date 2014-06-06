package servlets;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.Invoice;
import persistence.InvoiceDAO;

/**
 * Servlet pro pridavani faktur
 * @author Bc. Michal Vcelicka
 */
public class InvoiceAdd extends HttpServlet{

    /**
     * Obslouzi data prichazejici POSTem
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

        // ziskame parametry
        String order = checkNull(req.getParameter("id_order"));
        String date = checkNull(req.getParameter("date"));
        int id_order = Integer.valueOf(order);

        // vytvorime objednavku a ulozime
        InvoiceDAO catdao = new InvoiceDAO();
        Invoice cat = new Invoice(id_order, date);
        catdao.save(cat);
        resp.sendRedirect("/invoices?vlozeno=1");
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
