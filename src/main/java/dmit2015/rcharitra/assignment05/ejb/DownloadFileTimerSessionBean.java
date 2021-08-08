package dmit2015.rcharitra.assignment05.ejb;

import javax.annotation.Resource;
import javax.ejb.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 *
 * @Version 2021-04-03
 * @Author R Charitra
 * @Comments
 * This is class is used to download files and save the files at pre-designated location
 * The location and URI are hardcoded for this scenario however could be converted to
 * user assigned values from the front-end as well
 *
 * */
@Stateless  // Must be @Stateless or @Singleton. Timer service does not support Stateful session beans
public class DownloadFileTimerSessionBean {

    public static final String DOWNLOAD_URI = "https://data.edmonton.ca/api/views/ix8f-s9xp/rows.csv?accessType=DOWNLOAD";
    public static final String DOWNLOAD_DIRECTORY = "/home/user2015/Downloads/";

    @Resource   // This is a container created resource
    private TimerService _timerService;

   private Logger _logger = Logger.getLogger(DownloadFileTimerSessionBean.class.getName());
    // @Inject // Use only if your project includes a LoggerProducer
    // private Logger _logger;

    /**
     * The annotation @Timeout method is executed when a programmatic timer expires
     * @param timer contains information about the timer that expired
     */
    @Timeout
    public void timeout(Timer timer) {
        HttpClient client = HttpClient.newHttpClient();
        String downloadUriString = (DOWNLOAD_URI);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(downloadUriString))
                .build();
        String downloadDirectory = (DOWNLOAD_DIRECTORY);
        Path downloadPath = Path.of(downloadDirectory);
        try {
            HttpResponse<Path> response = client.send(request,
                    HttpResponse.BodyHandlers.ofFileDownload(downloadPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE));
            _logger.info("Finished download file to " + response.body());
        } catch (Exception e) {
            _logger.fine("Error downloading file. " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Create a new Timer with info that contains an HashMap of the downloadUri and the downloadDirectory
     * @param info an HashMap with value for the downloadUri and downloadDirectory
     * @param eventDateTime the start date time of the event
     * @return
//     */
    public Timer createTimer(HashMap<String, String> info, LocalDateTime eventDateTime) {
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo(info);
        ScheduleExpression eventScheduleExpression = TimerServiceHelper.toScheduleExpression(eventDateTime);
        return _timerService.createCalendarTimer(eventScheduleExpression, timerConfig);
    }

}