package scalability;

import com.google.appengine.api.quota.QuotaService;
import com.google.appengine.api.quota.QuotaServiceFactory;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Vojta
 */
public class LogFilter implements Filter {

    FilterConfig fconf = null;
    public static Measure m = new Measure();
    
    @Override
    public void init(FilterConfig fc) throws ServletException {
        this.fconf = fc;
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        QuotaService qs = QuotaServiceFactory.getQuotaService();
        if (qs.supports(QuotaService.DataType.CPU_TIME_IN_MEGACYCLES)) {
            long startAPI = qs.getApiTimeInMegaCycles();
            long startCPU = qs.getCpuTimeInMegaCycles();
            fc.doFilter(sr, sr1);
            long endCPU = qs.getCpuTimeInMegaCycles();
            long endAPI = qs.getApiTimeInMegaCycles();
            double cpuSeconds = qs.convertMegacyclesToCpuSeconds(endCPU - startCPU);
            double apiSeconds = qs.convertMegacyclesToCpuSeconds(endAPI - startAPI);
            System.out.println("Scalability: cpu = " + cpuSeconds + ", api = " + apiSeconds + ", sum = " + (cpuSeconds + apiSeconds));
             m.incCounter();
             m.addTime(cpuSeconds);
        } else {
            long startCPU = System.currentTimeMillis();
            fc.doFilter(sr, sr1);
            long endCPU = System.currentTimeMillis();
            //double cpuSeconds = (endCPU - startCPU) / 1000;
            double cpuSeconds = (endCPU - startCPU);
            System.out.println("Scalability: cpuSeconds = &" + cpuSeconds + "& (start = " + startCPU + "ms, end = " + endCPU + "ms)");
            m.incCounter();
            m.addTime(cpuSeconds);
            
            //items = items + 1;
            //System.out.println( m.getTime() + "/" + m.getItems());
        }
    }

    @Override
    public void destroy() {
        this.fconf = null;
    }
    
}
