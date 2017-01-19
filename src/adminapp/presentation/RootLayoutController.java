/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminapp.presentation;

import adminapp.presentation.currentTournamentTab.CurrentTournamentTabController;
import adminapp.presentation.manageTournamentsTab.ManageTournamentsTabController;
import adminapp.presentation.settingsTab.SettingsTabController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 *
 * @author Emilia
 */
public class RootLayoutController implements Initializable {

    private Stage primaryStage;

    @FXML
    TabPane tabs;

    @FXML
    private Tab tabManageTournaments;
    private ManageTournamentsTabController manageTournamentsController;
    @FXML
    private Tab tabCurrentTournament;
    private CurrentTournamentTabController currentTournamentController;
    @FXML
    private Tab tabSettings;
    private SettingsTabController settingsController;

    boolean initialized = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader0 = new FXMLLoader(getClass().getResource("manageTournamentsTab/ManageTournamentsTab.fxml"));
            tabs.getTabs().get(0).setContent(loader0.load());
            manageTournamentsController = loader0.getController();

            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("currentTournamentTab/CurrentTournamentTab.fxml"));
            tabs.getTabs().get(1).setContent(loader1.load());
            currentTournamentController = loader1.getController();
            currentTournamentController.setRootController(this);

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("settingsTab/SettingsTab.fxml"));
            tabs.getTabs().get(2).setContent(loader2.load());
            settingsController = loader2.getController();

        } catch (IOException ex) {
            Logger.getLogger(RootLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        tabs.getTabs().get(1).setDisable(true);
//        tabs.getTabs().get(2).setDisable(true);
//        tabs.getTabs().get(3).setDisable(true);
        initialized = true;
    }

    public void init() {
        System.out.println("adminapp.presentation.RootLayoutController.init()");
        currentTournamentController.init();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

}
