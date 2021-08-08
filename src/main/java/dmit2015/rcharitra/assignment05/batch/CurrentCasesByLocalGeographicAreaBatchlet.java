package dmit2015.rcharitra.assignment05.batch;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Polygon;
import org.geolatte.geom.codec.Wkt;
import org.geolatte.geom.crs.CoordinateReferenceSystems;

import dmit2015.rcharitra.assignment05.entity.CurrentCasesByLocalGeographicArea;
import dmit2015.rcharitra.assignment05.repository.CurrentCasesByLocalGeographicAreaRepository;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.security.Policy;
import java.util.Properties;

/**
 *
 * @Version 2021-04-03
 * @Author R Charitra
 * @Comments
 * This is a batchlet class used for batch processing
 * This class import csv files, parses through it creating objects and adds the objects to db
 *
 * */
@Named
public class CurrentCasesByLocalGeographicAreaBatchlet extends AbstractBatchlet {

    @Inject
    private CurrentCasesByLocalGeographicAreaRepository _repository;

    @Inject
    private JobContext _jobContext;
    /**
     * Perform a task and return "COMPLETED" if the job has successfully completed
     * otherwise return "FAILED" to indicate the job failed to complete.
     */
    @Transactional
    @Override
    public String process() throws Exception {
        Properties jobParameters = _jobContext.getProperties();
        String inputFile = jobParameters.getProperty("input_file");

        // For reading external files outside of the project use the code below:
        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(inputFile).toFile())))	{
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(_inputFile))))	{
            String line;
            final String delimiter = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
            // Skip the first line as it is containing column headings
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(delimiter);
                CurrentCasesByLocalGeographicArea model = new CurrentCasesByLocalGeographicArea();
                model.setLocation(values[0]);
                model.setTotalCases(Integer.parseInt(values[1]));
                model.setActiveCases(Integer.parseInt(values[2]));
                model.setRecoveredCases(Integer.parseInt(values[3]));
                model.setDeaths(Integer.parseInt(values[4]));

                Polygon<G2D> polygon = (Polygon<G2D>) Wkt.fromWkt(values[5].replaceAll("\"",""),
                        CoordinateReferenceSystems.WGS84);
                model.setPolygon(polygon);

                _repository.create(model);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "FAILED";
        }

        return "COMPLETED";        // The job has successfully completed
    }
}