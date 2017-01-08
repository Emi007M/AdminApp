/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminapp.presentation.settingsTab;

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

/**
 *
 * @author Emilia
 */
public class SettingsTabController implements Initializable {

    
    private RootLayoutController rootController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       

    }

    public void init() {
    
    }

    
    public void setRootController(RootLayoutController r) {
        rootController = r;
    }
}
