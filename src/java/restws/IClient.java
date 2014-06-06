package restws;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Jaroslav Kuchar
 */
public interface IClient {

    @GET
    @Path("/")
    @Produces("text/plain")
    String getComponents();

    @GET
    @Path("/")
    @Produces("application/xml")
    List getObjects();
}
