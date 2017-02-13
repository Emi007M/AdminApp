package adminapp.presentation.currentTournamentTab.views;

import adminapp.model.CurrentTournament;
import adminapp.model.Dictionary;
import adminapp.model.data.DataRegistration;
import adminapp.presentation.ViewBaseController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TitledPane;
import serializable.model.Competition;

/**
 *
 * @author Emilia
 */
public class ContestantsController extends ViewBaseController {

    @FXML
    private Button generateFormBtn;
    @FXML
    private Button importFormBtn;
    @FXML
    private Button saveAllBtn;

    @FXML
    private Accordion accordion;

    private ArrayList<ContestantsListViewController> listControllers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBackBtn();
        listControllers = new ArrayList<>();
        //init();

    }

    public void init() {

        int i = 0;
        for (Competition c : CurrentTournament.getTournamentCompetitions()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setResources(Dictionary.getBundle());
                loader.setLocation(getClass().getResource("ContestantsListView.fxml"));

                TitledPane pane = (TitledPane) loader.load();

                ContestantsListViewController controller = (ContestantsListViewController) loader.getController();

                controller.setCompetition(c);
                controller.init();

                listControllers.add(controller);

                accordion.getPanes().add(pane);

            } catch (IOException ex) {
                Logger.getLogger(ContestantsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    public void handleGenerateFormBtn() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(Dictionary.getString("dialog.form.title"));
        alert.setHeaderText(Dictionary.getString("dialog.form.info"));
        alert.setContentText(Dictionary.getString("dialog.form.text"));
        alert.showAndWait();

        DataRegistration.generateForm(CurrentTournament.getTournament(), Dictionary.getString("t2.application-form"), tabController.getRootLayoutController().getPrimaryStage());
    }

    @FXML
    public void handleImportFormBtn() {
        boolean success = DataRegistration.getData(CurrentTournament.getTournament(), tabController.getRootLayoutController().getPrimaryStage());
        listControllers.forEach((c) -> {
            c.updateList();
        });

        if (success) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(Dictionary.getString("dialog.import.title"));
            alert.setHeaderText(Dictionary.getString("dialog.import.info"));
            alert.setContentText(Dictionary.getString("dialog.import.text"));
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle(Dictionary.getString("dialog.import.title"));
            alert.setHeaderText(Dictionary.getString("dialog.import.info.fail"));
            alert.setContentText(Dictionary.getString("dialog.import.text.fail"));
            alert.showAndWait();
        }

    }

    @FXML
    public void handleSaveChangesBtn() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(Dictionary.getString("dialog.save.title"));
        alert.setHeaderText(Dictionary.getString("dialog.save.info"));
        alert.setContentText(Dictionary.getString("dialog.save.text"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            for (Competition c : CurrentTournament.getTournamentCompetitions()) {
                c.initChart();
            }
            CurrentTournament.getTournament().saveToFile();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

}
