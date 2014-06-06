package servlets;

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
public class CategoryAdd extends HttpServlet{

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
        CategoryDAO catdao = new CategoryDAO();
        Category cat = new Category(caption);
        catdao.save(cat);
        resp.sendRedirect("/categories?vlozeno=1");
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
