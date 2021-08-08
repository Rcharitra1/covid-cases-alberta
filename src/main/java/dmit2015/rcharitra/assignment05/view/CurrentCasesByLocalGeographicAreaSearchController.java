package dmit2015.rcharitra.assignment05.view;

import dmit2015.rcharitra.assignment05.entity.CurrentCasesByLocalGeographicArea;
import dmit2015.rcharitra.assignment05.repository.CurrentCasesByLocalGeographicAreaRepository;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 *  @Version 2021-04-05
 *  @Author R Charitra
 *  @Comments
 *  This is controlller class for search view.
 *  It has methods to to show and
* */

@Named("currentCurrentCasesByLocalGeographicAreaSearchController")
@ViewScoped
public class CurrentCasesByLocalGeographicAreaSearchController implements Serializable {

    @Inject
    private CurrentCasesByLocalGeographicAreaRepository _currentcasesbylocalgeographicareaRepository;

    @Getter
    @Setter
    private CurrentCasesByLocalGeographicArea existingCurrentCasesByLocalGeographicArea = new CurrentCasesByLocalGeographicArea();


    @Setter
    @Getter
    private double latitude;

    @Setter
    @Getter
    private double longitude;

    @Setter
    private Long editId=0L;


//    public void init() {
//        if (!Faces.isPostback()) {
//            if (!Faces.isPostback()) {
//                Optional<CurrentCasesByLocalGeographicArea> optional= _currentcasesbylocalgeographicareaRepository.findOneById(editId);
//                existingCurrentCasesByLocalGeographicArea = optional.get();
//            }
//        }
//    }


//    @PostConstruct
    public void onSearch() {
            try{
                Optional<CurrentCasesByLocalGeographicArea> optionalCurrentCasesByLocalGeographicArea = _currentcasesbylocalgeographicareaRepository.contains(longitude, latitude);
                if(optionalCurrentCasesByLocalGeographicArea.isPresent())
                {
                    existingCurrentCasesByLocalGeographicArea = optionalCurrentCasesByLocalGeographicArea.get();

                    editId = existingCurrentCasesByLocalGeographicArea.getId();
                    FacesContext.getCurrentInstance().getExternalContext().redirect("details.xhtml?editId=" + editId);
                }

                Messages.addGlobalError("No results for the provided lat and lang");

            }
            catch (Exception e)
            {
                Messages.addGlobalError("No results for the provided lat and lang");
            }
        }
    
        public void onClear()
        {
            longitude=0.0;
            latitude=0.0;
            Messages.addGlobalInfo("Successfully cleared");
        }


}