
package adminapp.presentation.currentTournamentTab;


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
import adminapp.presentation.RootLayoutController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Emilia
 */
public class NullBtnSetController implements Initializable {

//    @FXML
//    private AnchorPane rootPane;
//    @FXML
//    private Label titleLabel;
    @FXML
    private FlowPane mainFlow;

    @FXML
    private Button changeBtn;
  
    
    
    private CurrentTournamentTabController tabController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  titleLabel.setText(CurrentTournament.getTournamentTitle());

       
       changeBtn.setOnAction(e->handleChangeBtn());

    }

    public void init() {

    }

    
   
 
    
        @FXML
    private void handleChangeBtn() {
        System.out.println("change btn clicked...");
        tabController.setChooseTournament();
    }
    
    
    public void setTabController(CurrentTournamentTabController r) {
        tabController = r;
    }
}
