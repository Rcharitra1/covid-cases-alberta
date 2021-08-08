package dmit2015.rcharitra.assignment05.ejb;
/**
 *
 * @Version 2021-04-03
 * @Author R Charitra
 * @Comments
 * This is controller for the manage timer page
 *
 * */
import javax.ejb.Timer;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@SessionScoped
public class TimerController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private ProgrammaticTimersManagerBean timerBean;

    public String cancelAllTimers() {
        timerBean.cancelAllTimers();;
        return "";
    }

    public Collection<Timer> list() {
        return timerBean.listAllTimers();
    }

    public void cancelTimer(Timer selectedTimer) {
        timerBean.cancelTimer(selectedTimer);
    }
}