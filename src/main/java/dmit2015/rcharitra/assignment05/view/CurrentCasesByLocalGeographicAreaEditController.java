package dmit2015.rcharitra.assignment05.view;

import dmit2015.rcharitra.assignment05.entity.CurrentCasesByLocalGeographicArea;
import dmit2015.rcharitra.assignment05.repository.CurrentCasesByLocalGeographicAreaRepository;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;


/**
 *
 *
 * @Version 2021-04-05
 * @Author R Charitra
 * @Comments
 * This is controller class for details page
 *
 * */

@Named("currentCurrentCasesByLocalGeographicAreaEditController")
@ViewScoped
public class CurrentCasesByLocalGeographicAreaEditController implements Serializable {

    @Inject
    private CurrentCasesByLocalGeographicAreaRepository _currentcasesbylocalgeographicareaRepository;

    @Inject
    @ManagedProperty("#{param.editId}")
    @Getter
    @Setter
    private Long editId;

    @Getter
    private CurrentCasesByLocalGeographicArea existingCurrentCasesByLocalGeographicArea;

    @PostConstruct
    public void init() {
        if (!Faces.isPostback()) {
            Optional<CurrentCasesByLocalGeographicArea> optionalEntity = _currentcasesbylocalgeographicareaRepository.findOneById(editId);
            optionalEntity.ifPresent(entity -> existingCurrentCasesByLocalGeographicArea = entity);
        }
    }


}