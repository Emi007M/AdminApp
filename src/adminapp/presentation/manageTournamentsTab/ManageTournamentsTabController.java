package adminapp.presentation.manageTournamentsTab;

import adminapp.model.Dictionary;
import adminapp.presentation.LayoutBaseController;
import adminapp.presentation.manageTournamentsTab.views.NewTournamentController;
import adminapp.presentation.manageTournamentsTab.views.TournamentListController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
            loader.setResources(Dictionary.getBundle());
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
            loader.setResources(Dictionary.getBundle());
            loader.setLocation(getClass().getResource("views/TournamentList.fxml"));
            mainFlow.getChildren().clear();
            mainFlow.getChildren().setAll((FlowPane) loader.load());

            TournamentListController controller = (TournamentListController) loader.getController();
            controller.setTabController(this);

            controller.updateList();

        } catch (IOException ex) {
            Logger.getLogger(ManageTournamentsTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void setNewTournament() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(Dictionary.getBundle());
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
