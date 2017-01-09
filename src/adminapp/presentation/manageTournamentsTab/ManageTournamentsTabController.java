/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminapp.presentation.manageTournamentsTab;

import adminapp.presentation.manageTournamentsTab.views.TournamentListController;
import adminapp.presentation.manageTournamentsTab.views.NewTournamentController;
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
import adminapp.presentation.LayoutBaseController;
import adminapp.presentation.RootLayoutController;
import adminapp.presentation.ViewBaseController;

import adminapp.presentation.manageTournamentsTab.BtnSetController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Emilia
 */
public class ManageTournamentsTabController extends LayoutBaseController {

    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  titleLabel.setText(CurrentTournament.getTournamentTitle());
        init();

    }

    public void init() {
        setPrimaryBtnSet();
    }
    
    /*--Button views initialization--*/

    private void setPrimaryBtnSet() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("BtnSet.fxml"));
            mainFlow.getChildren().clear();
            mainFlow.getChildren().setAll((FlowPane) loader.load());

            BtnSetController controller = (BtnSetController) loader.getController();
            controller.setTabController(this);
        } catch (IOException ex) {
            Logger.getLogger(ManageTournamentsTabController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    /*--Executive views initialization--*/
    
    
    protected void setTournamentList() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("views/TournamentList.fxml"));
            mainFlow.getChildren().clear();
            mainFlow.getChildren().setAll((FlowPane) loader.load());

            TournamentListController controller = (TournamentListController) loader.getController();
            controller.setTabController(this);

        } catch (IOException ex) {
            Logger.getLogger(ManageTournamentsTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void setNewTournament() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("views/NewTournament.fxml"));
            mainFlow.getChildren().clear();
            mainFlow.getChildren().setAll((FlowPane) loader.load());

            NewTournamentController controller = (NewTournamentController) loader.getController();
            controller.setTabController(this);

        } catch (IOException ex) {
            Logger.getLogger(ManageTournamentsTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}
