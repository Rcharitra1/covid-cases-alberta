package dmit2015.rcharitra.assignment05.ejb;

import javax.annotation.Resource;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.*;
import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 *
 * @Version 2021-04-03
 * @Author R Charitra
 * @Comments
 * This is class is used to create timers to execute batch tasks
 * Created method to execute a batchjob automatically
 *
 *
 */
@Stateless  // Must be @Stateless or @Singleton. Timer service does not support Stateful session beans
public class BatchJobTimerSessionBean {

    @Resource   // This is a container created resource
    private TimerService _timerService;

   private Logger _logger = Logger.getLogger(BatchJobTimerSessionBean.class.getName());
    // @Inject // Use only if your project includes a LoggerProducer
    // private Logger _logger;

    /**
     * The annotation @Timeout method is executed when a programmatic timer expires
     * @param timer contains information about the timer that expired
     */
    @Timeout
    public void timeout(Timer timer) {
        try {
            // Get the name of the batch job XML file that included as information with the timer
            String batchJobXmlFilename = (String) timer.getInfo();
            // Get the JobOperator from the BatchRuntime
            JobOperator jobOperator = BatchRuntime.getJobOperator();
            // Create a new job instance and start the first execution of that instance asynchronously.
            long executionId = jobOperator.start(batchJobXmlFilename, null);
            _logger.info("Successfully started batch job with executionId " + executionId);
        } catch (Exception e) {
            _logger.fine(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Create a new Timer with info about the batch job XML filename.
     * @param batchJobXmlFilename the info to included with the TimerConfig
     * @param eventDateTime the start date time of the event
     * @return
     */
    public Timer createTimer(String batchJobXmlFilename, LocalDateTime eventDateTime) {
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo(batchJobXmlFilename);
        ScheduleExpression eventScheduleExpression = TimerServiceHelper.toScheduleExpression(eventDateTime);
        return _timerService.createCalendarTimer(eventScheduleExpression, timerConfig);
    }



    public void automaticTimeout(Timer timer, String batchJobName) {
        try {
            // Get the name of the batch job XML file that included as information with the timer
            String batchJobXmlFilename = batchJobName;
            // Get the JobOperator from the BatchRuntime
            JobOperator jobOperator = BatchRuntime.getJobOperator();
            // Create a new job instance and start the first execution of that instance asynchronously.
            long executionId = jobOperator.start(batchJobXmlFilename, null);
            _logger.info("Successfully started batch job with executionId " + executionId);
        } catch (Exception e) {
            _logger.fine(e.getMessage());
            e.printStackTrace();
        }
    }

}