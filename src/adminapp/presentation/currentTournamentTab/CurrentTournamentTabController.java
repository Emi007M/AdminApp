package adminapp.presentation.currentTournamentTab;

import adminapp.model.CurrentTournament;
import adminapp.model.Dictionary;
import adminapp.presentation.LayoutBaseController;
import adminapp.presentation.currentTournamentTab.views.ChooseTournamentController;
import adminapp.presentation.currentTournamentTab.views.ContestantsController;
import adminapp.presentation.currentTournamentTab.views.ManageBoardsController;
import adminapp.presentation.currentTournamentTab.views.ResultsController;
import adminapp.presentation.currentTournamentTab.views.StartingListsController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Emilia
 */
public class CurrentTournamentTabController extends LayoutBaseController {

    @FXML
    private Label nameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        init();

    }

    public void init() {
        //if sth then
        if (CurrentTournament.getTournament() == null) {
            setNullBtnSet();
            nameLabel.setText("");
        } else {
            setPrimaryBtnSet();
            nameLabel.setText(CurrentTournament.getTournamentTitle());
        }
    }

    /*--Button views initialization--*/
    private void setPrimaryBtnSet() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(Dictionary.getBundle());
            loader.setLocation(getClass().getResource("PrimaryBtnSet.fxml"));
            mainFlow.getChildren().clear();
            mainFlow.getChildren().setAll((FlowPane) loader.load());

            PrimaryBtnSetController controller = (PrimaryBtnSetController) loader.getController();
            controller.setTabController(this);
        } catch (IOException ex) {
            Logger.getLogger(CurrentTournamentTabController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setNullBtnSet() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(Dictionary.getBundle());
            loader.setLocation(getClass().getResource("NullBtnSet.fxml"));
            mainFlow.getChildren().clear();
            mainFlow.getChildren().setAll((FlowPane) loader.load());

            NullBtnSetController controller = (NullBtnSetController) loader.getController();
            controller.setTabController(this);
        } catch (IOException ex) {
            Logger.getLogger(CurrentTournamentTabController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*--Executive views initialization--*/
    protected void setContestants() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(Dictionary.getBundle());
            loader.setLocation(getClass().getResource("views/Contestants.fxml"));
            mainFlow.getChildren().clear();
            mainFlow.getChildren().setAll((FlowPane) loader.load());

            ContestantsController controller = (ContestantsController) loader.getController();
            controller.setTabController(this);

            controller.init();

        } catch (IOException ex) {
            Logger.getLogger(CurrentTournamentTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void setStartingLists() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(Dictionary.getBundle());
            loader.setLocation(getClass().getResource("views/StartingLists.fxml"));
            mainFlow.getChildren().clear();
            mainFlow.getChildren().setAll((FlowPane) loader.load());

            StartingListsController controller = (StartingListsController) loader.getController();
            controller.setTabController(this);

        } catch (IOException ex) {
            Logger.getLogger(CurrentTournamentTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void setManageBoards() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(Dictionary.getBundle());
            loader.setLocation(getClass().getResource("views/ManageBoards.fxml"));
            mainFlow.getChildren().clear();
            mainFlow.getChildren().setAll((FlowPane) loader.load());

            ManageBoardsController controller = (ManageBoardsController) loader.getController();
            controller.setTabController(this);

            controller.init();

        } catch (IOException ex) {
            Logger.getLogger(CurrentTournamentTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void setResults() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(Dictionary.getBundle());
            loader.setLocation(getClass().getResource("views/Results.fxml"));
            mainFlow.getChildren().clear();
            mainFlow.getChildren().setAll((FlowPane) loader.load());

            ResultsController controller = (ResultsController) loader.getController();
            controller.setTabController(this);

        } catch (IOException ex) {
            Logger.getLogger(CurrentTournamentTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void setChooseTournament() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(Dictionary.getBundle());
            loader.setLocation(getClass().getResource("views/ChooseTournament.fxml"));
            mainFlow.getChildren().clear();
            mainFlow.getChildren().setAll((FlowPane) loader.load());

            ChooseTournamentController controller = (ChooseTournamentController) loader.getController();
            controller.setTabController(this);
            controller.updateList();

        } catch (IOException ex) {
            Logger.getLogger(CurrentTournamentTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
