package dmit2015.rcharitra.assignment05.producer;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @Version 2021-04-03
 * @Author R Charitra
 * @Comments
 * This is class is used for logging errors during production
 * since we dont have access to console
 * */

public class LoggerProducer {
    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        return
                Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
