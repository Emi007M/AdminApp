
package adminapp.presentation.manageTournamentsTab;


import adminapp.presentation.currentTournamentTab.*;
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
import adminapp.model.Competition;
import adminapp.model.CurrentTournament;
import adminapp.presentation.RootLayoutController;
import adminapp.presentation.ViewBaseController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Emilia
 */
public class BtnSetController extends ViewBaseController {



    @FXML
    private Button listBtn, newBtn;
  
    
    
    private ManageTournamentsTabController tabController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
       listBtn.setOnAction(e->handleListBtn());
       newBtn.setOnAction(e->handleNewBtn());

    }

    public void init() {

    }

    
   
 

    @FXML
    private void handleListBtn() {
        System.out.println("list btn clicked...");
        tabController.setTournamentList();
    }
    
    @FXML
    private void handleNewBtn() {
        System.out.println("new btn clicked...");
        tabController.setNewTournament();
    }
    
    
    public void setTabController(ManageTournamentsTabController r) {
        tabController = r;
    }
}
