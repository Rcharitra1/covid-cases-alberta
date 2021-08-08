package dmit2015.rcharitra.assignment05.view;

import dmit2015.rcharitra.assignment05.entity.CurrentCasesByLocalGeographicArea;
import dmit2015.rcharitra.assignment05.repository.CurrentCasesByLocalGeographicAreaRepository;
import org.omnifaces.util.Messages;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/***
 *
 * @Version 2021-04-05
 * @Author R Charitra
 * @Comments
 *
 * This is controller class for covid 19 cases listing page
 *
 *
 */

@Named("currentCurrentCasesByLocalGeographicAreaListController")
@ViewScoped
public class CurrentCasesByLocalGeographicAreaListController implements Serializable {

    @Inject
    private CurrentCasesByLocalGeographicAreaRepository _currentcasesbylocalgeographicareaRepository;

    @Getter
    private List<CurrentCasesByLocalGeographicArea> currentcasesbylocalgeographicareaList;

    @PostConstruct  // After @Inject is complete
    public void init() {
        try {
            currentcasesbylocalgeographicareaList = _currentcasesbylocalgeographicareaRepository.list();
            Comparator<CurrentCasesByLocalGeographicArea> compareById = Comparator.comparing(CurrentCasesByLocalGeographicArea::getId);
            Collections.sort(currentcasesbylocalgeographicareaList,compareById);
        } catch (RuntimeException ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }
}