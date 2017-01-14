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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableArray;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import serializable.model.Person;

/**
 *
 * @author Emilia
 */
public class ContestantsListViewController implements Initializable {
    
    @FXML
    private TitledPane rootPane;
            
    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, String> colClub;

    @FXML
    private TableColumn<Person, String> colName;

    @FXML
    private TableColumn<Person, String> colSurname;

    @FXML
    private TableColumn<Person, String> colDegree;

    @FXML
    private TableColumn<Person, Integer> colYear;

    @FXML
    private Button AddBtn;

    @FXML
    private Button removeBtn;
    
    private Competition competition;
    private ObservableList<Person> people;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setBackBtn();
        
        competition = null;
        
        people =  FXCollections.observableArrayList();  
        tableView.setItems(people);
        
        
        
    }

    public void init(){
        initTableView();
    }
    

    public void setCompetition(Competition c){
        this.competition = c;
    }
    
    
    private void initTableView(){
        this.rootPane.setText("List "+competition.getID()+". "+competition.getTitle());
        
        this.people.addAll(competition.getCompetitors());
        
        
        
    }
    
    public void updateList(){
        this.people.setAll(competition.getCompetitors());
    }

   
}
