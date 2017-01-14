package adminapp.presentation.currentTournamentTab.views;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import serializable.model.Competition;
import adminapp.model.CurrentTournament;
import adminapp.model.DataRegistration;
import adminapp.presentation.RootLayoutController;
import adminapp.presentation.ViewBaseController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import serializable.model.Tournament;

/**
 *
 * @author Emilia
 */
public class ContestantsController extends ViewBaseController {

    @FXML
    private Button generateFormBtn;
    @FXML
    private Button importFormBtn;
    @FXML
    private Button saveAllBtn;
    
    
    @FXML
    private Accordion accordion;
    
    

    private ArrayList<ContestantsListViewController> listControllers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBackBtn();
        listControllers = new ArrayList<>();
        //init();

    }

    public void init() {

        int i = 0;
        for (Competition c : CurrentTournament.getTournamentCompetitions()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ContestantsListView.fxml"));

                TitledPane pane = (TitledPane) loader.load();
                
                ContestantsListViewController controller = (ContestantsListViewController) loader.getController();

                controller.setCompetition(c);
                controller.init();

                listControllers.add(controller);
               
               
                accordion.getPanes().add(pane);
                
            } catch (IOException ex) {
                Logger.getLogger(ContestantsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    
    @FXML
    public void handleGenerateFormBtn(){
        DataRegistration.generateForm(CurrentTournament.getTournament(), "formularz zgloszeniowy", tabController.getRootLayoutController().getPrimaryStage());
    }
    
    @FXML
    public void handleImportFormBtn(){
        DataRegistration.getData(CurrentTournament.getTournament(), tabController.getRootLayoutController().getPrimaryStage());
        listControllers.forEach((c) -> {
            c.updateList();
        });
        
    }
    
    @FXML
    public void handleSaveChangesBtn(){
        CurrentTournament.getTournament().saveToFile();
    }
    
    
}
