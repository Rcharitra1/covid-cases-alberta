package dmit2015.rcharitra.assignment05.batch;

import dmit2015.rcharitra.assignment05.repository.CurrentCasesByLocalGeographicAreaRepository;

import javax.batch.api.listener.AbstractJobListener;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.util.logging.Logger;


/**
 *
 * @Version 2021-04-03
 * @Author R Charitra
 * @Comments
 * This is a listener class that is executed after and before a batch is executed
 * Have added a code block to check if the file is already present and delete before the job is executed
 *
 * */
@Named
public class CurrentCasesByLocalGeographicAreaJobListener extends AbstractJobListener {

    @Inject
    private JobContext _jobContext;

    @Inject
    private Logger _logger;

    private long startTime;

    @Inject
    private CurrentCasesByLocalGeographicAreaRepository _repository;

    @Override
    public void beforeJob() throws Exception {

        String url = "/home/user2015/Downloads/COVID-19_in_Alberta__Current_cases_by_local_geographic_area.csv";
        File file = new File(url);
        if(file.exists())
        {
            try {
                file.delete();
                _logger.info("file successfully deleted");
            }catch (Exception e)
            {
                _logger.info("file delete failed");
            }
        }


        _logger.info(_jobContext.getJobName() + " beforeJob");
        startTime = System.currentTimeMillis();
        // Delete all records from the data source as we need to re-create all the records
        _repository.deleteAll();


    }

    @Override
    public void afterJob() throws Exception {
        _logger.info(_jobContext.getJobName() + "afterJob");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        String message = _jobContext.getJobName() + " completed in " + duration + " milliseconds";
        _logger.info(message);

    }

}