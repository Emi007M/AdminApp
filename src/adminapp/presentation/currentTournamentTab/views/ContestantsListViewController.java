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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableArray;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;
import javafx.util.converter.IntegerStringConverter;
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
    private TableColumn<Person, Integer> colRanked;

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

        people = FXCollections.observableArrayList();
        tableView.setItems(people);

        setTableEditable();
    }

    public void init() {
        initTableView();

    }

    public void setCompetition(Competition c) {
        this.competition = c;
    }

    private void initTableView() {
        this.rootPane.setText("List " + competition.getID() + ". " + competition.getTitle());

        this.people.addAll(competition.getCompetitors());

    }

    private void setTableEditable() {
        colClub.setCellFactory(TextFieldTableCell.forTableColumn());
        colClub.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
            @Override
            public void handle(CellEditEvent<Person, String> t) {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setClub(t.getNewValue());
            }
        }
        );

        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colName.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
            @Override
            public void handle(CellEditEvent<Person, String> t) {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setName(t.getNewValue());
            }
        }
        );

        colSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        colSurname.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
            @Override
            public void handle(CellEditEvent<Person, String> t) {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setSurname(t.getNewValue());
            }
        }
        );

        colDegree.setCellFactory(TextFieldTableCell.forTableColumn());
        colDegree.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
            @Override
            public void handle(CellEditEvent<Person, String> t) {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setDegree(t.getNewValue());
            }
        }
        );

        colYear.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colYear.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, Integer>>() {
            @Override
            public void handle(CellEditEvent<Person, Integer> t) {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setYear(t.getNewValue());
            }
        }
        );

        colRanked.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colRanked.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, Integer>>() {
            @Override
            public void handle(CellEditEvent<Person, Integer> t) {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setRank(t.getNewValue());
            }
        }
        );

    }

    public void updateList() {
        this.people.setAll(competition.getCompetitors());
    }

    @FXML
    public void handleRemoveBtn() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Athlete Romoving");
        alert.setHeaderText("Remove athlete");
        alert.setContentText("Are you sure, you want to remove this ahlete from the list?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            Person selectedItem = tableView.getSelectionModel().getSelectedItem();
            tableView.getItems().remove(selectedItem);

            competition.removePlayer(selectedItem);
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }
    
    @FXML
    public void handleAddBtn() {
        Person p = new Person("NAME", "SURNAME", 1111, "x KYU", "CLUB");
        competition.addPlayer(p);
        people.add(p);
    }

}
