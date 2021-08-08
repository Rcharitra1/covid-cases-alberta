package dmit2015.rcharitra.assignment05.ejb;

import javax.ejb.ScheduleExpression;
import java.time.LocalDateTime;



/**
 *
 * @Version 2021-04-03
 * @Author R Charitra
 * @Comments
 * This is a helper class since EJB doesnt support LocalDateTime it contains method to convert
 * to ScheduleExpression
 *
 * */
public class TimerServiceHelper {

    /**
     * Helper method for converting a LocalDateTime to a ScheduledExpression.
     *
     * @param fromDataTime the LocalDateTime to convert to ScheduleExpression
     * @return the ScheduleExpression created using the LocalDateTime
     */
    public static ScheduleExpression toScheduleExpression(LocalDateTime fromDataTime) {
        ScheduleExpression scheduleExpression = new ScheduleExpression();
        scheduleExpression.year(fromDataTime.getYear());
        scheduleExpression.month(fromDataTime.getMonth().getValue());  // Both ScheduleExpression and LocalDateTime uses a month number from 1 to 12.
        scheduleExpression.dayOfMonth(fromDataTime.getDayOfMonth());
        scheduleExpression.hour(fromDataTime.getHour());
        scheduleExpression.minute(fromDataTime.getMinute());
        scheduleExpression.second(fromDataTime.getSecond());
        return scheduleExpression;
    }
}