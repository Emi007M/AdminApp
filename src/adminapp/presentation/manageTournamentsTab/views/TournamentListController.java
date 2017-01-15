/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminapp.presentation.manageTournamentsTab.views;

import adminapp.presentation.ViewBaseController;
import com.jfoenix.controls.JFXButton;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import serializable.model.Competition;
import serializable.model.Serializator;
import serializable.model.Tournament;

/**
 *
 * @author Emilia
 */
public class TournamentListController extends ViewBaseController {

    @FXML
    private JFXButton reloadBtn;

    @FXML
    private VBox VBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBackBtn();
    }

    public void updateList() {
        
        VBox.getChildren().clear();
        
        //get tournaments from folder
        ArrayList<Serializable> objs;
        objs = Serializator.readAllFromFolder("tournaments");

        //print tournaments
        for (Serializable s : objs) {
            Tournament t = (Tournament) s;

            Label title = new Label("(" + t.getDate() + ") " + t.getTitle());
            title.getStyleClass().add("tournamentTitleList");

            VBox box = new VBox(title);

            for (Competition c : t.getCompetitions()) {
                box.getChildren().add(new Label(" - " + c.getTitle()));
            }

            VBox.getChildren().add(box);

        }
    }
}
