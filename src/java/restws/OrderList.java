package restws;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import persistence.Orderx;
import persistence.OrderxDAO;

/**
 * Webová služba pro dodavatele
 * @author Bc. Vojtěch Svoboda <svobovo3@fel.cvut.cz>
 */
@Path("/")
public class OrderList {

    /**
     * Vytahneme vsechny dodavatele jako XML
     * @return List dodavatele.xml
     */
    @GET
    @Path("/orders/")
    @Produces("application/xml")
    public List<Orderx> asXML() {
        OrderxDAO cda = new OrderxDAO();
        List<Orderx> list =  cda.getAll();
        Iterator iterator = list.iterator();
        Orderx supp;
        for ( ; iterator.hasNext() ; ) {
            supp = (Orderx) iterator.next();
            supp.setWebkey(supp.getWebkey());
        }
        return list;
    }

    /**
     * Vložení jednoho dodavatele
     * @param c
     */
    @POST
    @Path("/orders/")
    @Consumes("application/xml")
    public void save(Orderx c) {
        OrderxDAO cda = new OrderxDAO();
        cda.save(c);
    }

    /**
     * Vložení jednoho dodavatele
     * @param c
     */
    @DELETE
    @Path("/order/{id}/")
    @Produces("application/xml")
    public void delete(@PathParam("id") String id) {
        OrderxDAO cda = new OrderxDAO();
        Key k = KeyFactory.stringToKey(id);
        Orderx sup = cda.get(k);
        cda.delete(sup);
    }

    /**
     * Vytahnuti jednoho dodavatele
     * @param id
     * @return Supplier dodavatel
     */
    @GET
    @Path("/order/{id}/")
    @Produces("application/xml")
    public Orderx getSupplier(@PathParam("id") String id) {
        OrderxDAO sda = new OrderxDAO();
        Key k = KeyFactory.stringToKey(id);
        Orderx s = sda.get(k);
        return s;
    }
}
