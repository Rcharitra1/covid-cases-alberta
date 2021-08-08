package dmit2015.rcharitra.assignment05.ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import java.util.Collection;

/**
 *
 * @Version 2021-04-03
 * @Author R Charitra
 * @Comments
 * This is class is used to manage all the currently scheduled timers
 *
 * */

@Stateless	// Timer service does not support Stateful session beans
public class ProgrammaticTimersManagerBean {

	@Resource	// This is a container created resource
	TimerService timerService;

    /**
     * Cancel all active timers associated with the beans in the same module in which the caller bean is packaged.
     * These include both the programmatically-created timers and the automatically-created timers.
     */
    public void cancelAllTimers() {
        for(Timer singleTimer : timerService.getAllTimers()) {
            singleTimer.cancel();
        }
    }

    /**
     * Cancel the selectedTimer and all its associated expiration notifications.
     * @param selectedTimer the Timer to cancel.
     */
    public void cancelTimer(Timer selectedTimer) {
        selectedTimer.cancel();
    }


    /**
     * Returns all active timers associated with the beans in the same module in which the caller bean is packaged.
     * These include both the programmatically-created timers and the automatically-created timers.
     *
     * @return all active timers associated with the beans in the same module in which the caller bean is packaged.
     */
    public Collection<Timer> listAllTimers() {
        return timerService.getAllTimers();
    }

}