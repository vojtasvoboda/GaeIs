package restws;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.executors.URLConnectionClientExecutor;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import persistence.BlackList;

/**
 * Webová služba pro získání blacklistu
 * @author Bc. Vojtěch Svoboda <svobovo3@fel.cvut.cz>
 */
@Path("/blacklist/")
public class BlackListRemote {

    @GET
    @Path("/")
    @Produces("text/plain")
    public String getList() {
        RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
        final ClientExecutor clientExecutor = new URLConnectionClientExecutor();
        IClient client = ProxyFactory.create(IClient.class, "http://dev.vranam.cz/knihovna/rest/", clientExecutor);
        String s = client.getComponents();
        return s;
    }

    @GET
    @Path("/")
    @Produces("application/xml")
    public List<BlackList> getObjectList() {
        RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
        final ClientExecutor clientExecutor = new URLConnectionClientExecutor();
        IClient client = ProxyFactory.create(IClient.class, "http://dev.vranam.cz/knihovna/rest/", clientExecutor);
        List<BlackList> s = client.getObjects();
        return s;
    }
}
