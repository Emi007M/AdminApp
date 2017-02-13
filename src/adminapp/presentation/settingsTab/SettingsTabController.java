package adminapp.presentation.settingsTab;

import adminapp.model.SocketServer;
import adminapp.presentation.RootLayoutController;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

        if (serverOn) {
            return;
        }

        InetAddress IP;
        try {
            IP = InetAddress.getLocalHost();
            serverInfo.setText("IP: " + IP.getHostAddress());
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
