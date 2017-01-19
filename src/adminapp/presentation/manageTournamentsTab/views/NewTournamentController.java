/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminapp.presentation.manageTournamentsTab.views;

import adminapp.presentation.ViewBaseController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import serializable.model.Competition;
import serializable.model.Tournament;

/**
 *
 * @author Emilia
 */
public class NewTournamentController extends ViewBaseController {

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXDatePicker dateField;
    @FXML
    private JFXTextField placeField;

    @FXML
    private JFXTreeTableView<?> tableView;

    @FXML
    private TreeTableColumn<?, ?> idCol;

    @FXML
    private TreeTableColumn<?, ?> titleCol;

    @FXML
    private TreeTableColumn<?, ?> typeCol;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton addBtn;

    @FXML
    private VBox VBox;
    @FXML
    private GridPane list_01;
    @FXML
    private JFXTextField gridId;
    @FXML
    private JFXTextField gridName;
    @FXML
    private JFXTextArea gridDescr;
    @FXML
    private Spinner<?> gridType;

    private Integer idCounter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBackBtn();
        idCounter = 1;
    }

    @FXML
    private void handleAddListBtn() {

        System.out.println("add list btn clicked");
        try {
            // Load list layout from fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("StartingListLayout.fxml"));

            //initialize new list
            GridPane g = (GridPane) loader.load();
            g.setId((idCounter).toString());
            JFXTextField id = (JFXTextField) g.getChildren().get(0);
            id.setText(idCounter.toString());
            idCounter++;

            VBox.getChildren().add(g);

        } catch (IOException ex) {
            Logger.getLogger(NewTournamentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //kata Heian 1-2 oceniane systemem chorągiewkowym, dostosowane do pasa, bez repasaży

    @FXML
    private void handleSaveBtn() {
        System.out.println("save btn clicked");
        //Date date = new Date(dateField.getValue().getYear(), dateField.getValue().getMonthValue(), dateField.getValue().getDayOfMonth());
        LocalDate date = dateField.getValue();

        Tournament t = new Tournament(nameField.getText(), date, placeField.getText());

//        System.out.print(date2);
//        System.out.println(t.getDateAsDashString());
        //add competitions
        ArrayList<Competition> competitions = new ArrayList<>();

        for (Node n : VBox.getChildren()) {

            GridPane g = (GridPane) n;
            JFXTextField id = (JFXTextField) g.getChildren().get(0);
            JFXTextField name = (JFXTextField) g.getChildren().get(1);
            JFXTextArea descr = (JFXTextArea) g.getChildren().get(2);

            Competition c = new Competition();
            c.setTitle(name.getText());
            c.setDescr(descr.getText());
            c.setID(Integer.parseInt(id.getText()));
            c.setTwoThirdPlaces(true);
            c.setBoardID(1);
            c.initChart();
            competitions.add(c);
        }

        t.setCompetitions(competitions);

        //save to file
        t.saveToFile();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tournament Created");
        alert.setHeaderText("Tournament succesfully created");
        alert.setContentText("Tournament has been succesfully created and saved.");
        alert.showAndWait();
    }
}
