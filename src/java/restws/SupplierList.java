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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import persistence.Supplier;
import persistence.SupplierDAO;

/**
 * Webová služba pro dodavatele
 * @author Bc. Vojtěch Svoboda <svobovo3@fel.cvut.cz>
 */
@Path("/")
public class SupplierList {

    /**
     * Vytahneme vsechny dodavatele jako XML
     * @return List dodavatele.xml
     */
    @GET
    @Path("/suppliers/")
    @Produces("application/xml")
    public List<Supplier> asXML() {
        SupplierDAO cda = new SupplierDAO();
        List<Supplier> list =  cda.getAll();
        Iterator iterator = list.iterator();
        Supplier supp;
        for ( ; iterator.hasNext() ; ) {
            supp = (Supplier) iterator.next();
            supp.setWebkey(supp.getWebkey());
        }
        return list;
    }

    /**
     * Vložení jednoho dodavatele
     * @param c
     */
    @POST
    @Path("/suppliers/")
    @Consumes("application/xml")
    public Response save(Supplier c) {
        ResponseBuilder builder;
        try{
            SupplierDAO cda = new SupplierDAO();
            cda.save(c);
            builder = Response.status(Response.Status.CREATED);
            builder.entity("<Result>OK</Result>");
            
        } catch (Exception e) {
            builder = Response.status(Response.Status.NOT_ACCEPTABLE);
            builder.entity("<Result>Failed</Result>");
        }
            return builder.build();


    }

    /**
     * Vytahnuti jednoho dodavatele
     * @param id
     * @return Supplier dodavatel
     */
    @GET
    @Path("/supplier/{id}/")
    @Produces("application/xml")
    //public Supplier getSupplier(@PathParam("id") String id) {
    public Response getSupplier(@PathParam("id") String id) {
        SupplierDAO sda = new SupplierDAO();
        ResponseBuilder builder;
        try {
            Key k = KeyFactory.stringToKey(id);
            Supplier s = sda.get(k);
            builder = Response.status(Response.Status.OK);
            builder.entity(s);
        } catch (Exception e)  {
           
            builder = Response.status(Response.Status.NOT_FOUND);
            builder.entity("<Error>Not found</Error>");
        }
        


       
        return builder.build();
    }

    /**
     * Smazeme jednoho dodavatele
     * @param id
     * @return
     */
    @DELETE
    @Path("/supplier/{id}/")
    @Produces("application/xml")
    public Response deleteSupplier(@PathParam("id") String id) {
        SupplierDAO sda = new SupplierDAO();
        ResponseBuilder builder;
        try {
            Key k = KeyFactory.stringToKey(id);
            Supplier s = sda.get(k);
            builder = Response.status(Response.Status.OK);
            builder.entity("<Result>OK</Result>");
            sda.delete(s);
        } catch (Exception e) {
            System.out.println(e);
            builder = Response.status(Response.Status.NOT_FOUND);
            builder.entity("<Error>Not found</Error>");
        }
        return builder.build();
    }
}
