/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminapp.presentation;

import adminapp.presentation.manageTournamentsTab.ManageTournamentsTabController;
import adminapp.presentation.manageTournamentsTab.BtnSetController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Emilia
 */
public abstract class LayoutBaseController implements Initializable {
     @FXML
    protected FlowPane mainFlow;

   
    protected RootLayoutController rootController;

    @Override
    abstract public void initialize(URL location, ResourceBundle resources);
    //  titleLabel.setText(CurrentTournament.getTournamentTitle());

    abstract public void init();
    
   
    
    
    public void setRootController(RootLayoutController r) {
        rootController = r;
    }
    
    public RootLayoutController getRootLayoutController(){
        return rootController;
    }

}
