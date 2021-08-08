package dmit2015.rcharitra.assignment05.ejb;


/**
 *
 * @Version 2021-04-03
 * @Author R Charitra
 * @Comments
 * This is class is used to schedule an automatic timer
 * created method to execute automatic batch job
 *
 * */

import org.omnifaces.util.Messages;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Singleton
@Startup
public class AutomaticTimerBean {

    @Resource   // This is a container created resource
    private TimerService _timerService;


    @Inject

    private DownloadFileTimerSessionBean _downloadCsvFileTimerSessionBean;

    @Inject
    private BatchJobTimerSessionBean _batchJobTimerSessionBean;

    private Logger _logger = Logger.getLogger(AutomaticTimerBean.class.getName());



    @Schedule(second = "0", minute = "00", hour="16", dayOfWeek ="Mon-Fri", month = "Jan-Dec",year="2021", info = "Download COVID 19 data for Alberta", persistent = false)
    public void downloadCurrentCasesByLocalGeographicAreaCSV(Timer timer)
    {
        _downloadCsvFileTimerSessionBean.timeout(timer);
    }

    @Schedule(second = "0", minute = "01", hour="16", dayOfWeek ="Mon-Fri", month = "Jan-Dec",year="2021", info = "Add Covid 19 data to Oracle Db", persistent = false)
    public void executeBatchCommandAfterDownload(Timer timer)
    {

        _batchJobTimerSessionBean.automaticTimeout(timer, "batchletCurrentCasesByLocalGeographicArea");
    }







}
