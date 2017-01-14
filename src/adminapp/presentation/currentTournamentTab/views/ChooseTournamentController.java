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
import adminapp.presentation.RootLayoutController;
import adminapp.presentation.ViewBaseController;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableArray;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import serializable.model.Person;
import serializable.model.Serializator;
import serializable.model.Tournament;

/**
 *
 * @author Emilia
 */
public class ChooseTournamentController extends ViewBaseController {
    
   @FXML
    private VBox VBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBackBtn();
    }

    
    
    public void updateList(){
        //get tournaments from folder
        ArrayList<Serializable> objs;
        objs = Serializator.readAllFromFolder("tournaments");
        
        
        //print tournaments
        for(Serializable s : objs){
            Tournament t = (Tournament) s;
            
            Button btn = new Button("("+t.getDate()+") "+t.getTitle()); 
            btn.getStyleClass().add("tournamentBtn");
            if(CurrentTournament.getTournament()!=null && 
               t.getDate().equals(CurrentTournament.getTournament().getDate()) && 
               t.getTitle().equals(CurrentTournament.getTournament().getTitle())  )
                btn.getStyleClass().add("current");
            btn.setOnAction(e->setTournament(t));
                    
            VBox.getChildren().add(btn);
            
        }
    }
    
    private void setTournament(Tournament t){
        CurrentTournament.setTournament(t);
        tabController.init();
    }
    


   
}
