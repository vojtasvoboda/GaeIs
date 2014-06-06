/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package restws;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import persistence.Supplier;
import persistence.SupplierDAO;
import java.util.Random;
import scalability.Measure;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import scalability.LogFilter;


/**
 *
 * @author dispo
 */
@Path("/")
public class ScalabilityTest {

    @GET
    @Path("/testDBOperation/")
    @Produces("text/plain")
    public void testDB10() {
       SupplierDAO dao = new SupplierDAO();
        
       for (int i = 0; i < 10; i++) {
            
            Supplier supp = new Supplier("12345678", "CZPOKUS", "Pod mostem 15, Praha 1", "POKUS_MERENI", "FANTOMAS");
            dao.save(supp);
            dao.delete(supp);
        }
       
        
}

    @GET
    @Path("/testDBOperation/")
    @Produces("text/plain")
    public void testPerf() {
        Random rndGen = new Random();
        rndGen.setSeed(System.currentTimeMillis());
        double rnd;
        rnd = rndGen.nextDouble();


       for (int i = 0; i < 1000; i++) {
            rnd = Math.sqrt(rnd);

        }

        
}

    @GET
    @Path("/clearCounter/")
    @Produces("text/plain")
    public void clearCounter() {
        //Measure.clearCounter();
        LogFilter.m.clearCounter();
        }


    @GET
    @Path("/getCounter/")
    @Produces("application/xml")
    public Response getAvg() {
       // scalability.Measure.
        ResponseBuilder builder;
        builder = Response.status(Response.Status.OK);
        builder.entity("<Result>"+LogFilter.m.getAvg()+ "/" + LogFilter.m.getTime() + "/" + LogFilter.m.getItems() + "</Result>");

        
        return builder.build();
        
}

}
