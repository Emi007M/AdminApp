package adminapp.presentation.manageTournamentsTab;

import adminapp.presentation.ViewBaseController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author Emilia
 */
public class BtnSetController extends ViewBaseController {

    @FXML
    private Button listBtn, newBtn;

    private ManageTournamentsTabController tabController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listBtn.setOnAction(e -> handleListBtn());
        newBtn.setOnAction(e -> handleNewBtn());

    }

    public void init() {

    }

    @FXML
    private void handleListBtn() {
        System.out.println("list btn clicked...");
        tabController.setTournamentList();
    }

    @FXML
    private void handleNewBtn() {
        System.out.println("new btn clicked...");
        tabController.setNewTournament();
    }

    public void setTabController(ManageTournamentsTabController r) {
        tabController = r;
    }
}
