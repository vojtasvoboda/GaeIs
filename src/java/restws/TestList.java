package restws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Vojta Svoboda
 */
@Path("/")
public class TestList {

    @GET
    @Path("/test1")
    @Produces("text/plain")
    public String asString(){
        return "testString";
    }

    @GET
    @Path("/test2")
    @Produces("application/xml")
    public String asXML(){
        return "testXML";
    }
}
