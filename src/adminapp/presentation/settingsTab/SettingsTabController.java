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
import adminapp.model.SocketServer;
import adminapp.presentation.RootLayoutController;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

/**
 *
 * @author Emilia
 */
public class SettingsTabController implements Initializable {

    private RootLayoutController rootController;
    
    private boolean serverOn = false;

    @FXML 
    private Label serverInfo;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void init() {

    }

    public void setRootController(RootLayoutController r) {
        rootController = r;
    }

    @FXML
    private void handleServerBtn() {


        if(serverOn) return;
        
        InetAddress IP;
        try {
            IP = InetAddress.getLocalHost();
            serverInfo.setText("IP: "+IP.getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(SettingsTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Thread th = new Thread(() -> {
            try {
                SocketServer.runServer();
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(SettingsTabController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SettingsTabController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        th.setDaemon(true);
        th.start();

        serverOn = true;
                 
    }
}
