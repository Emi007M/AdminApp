package adminapp.presentation.currentTournamentTab;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Emilia
 */
public class NullBtnSetController implements Initializable {

    @FXML
    private FlowPane mainFlow;

    @FXML
    private Button changeBtn;

    private CurrentTournamentTabController tabController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  titleLabel.setText(CurrentTournament.getTournamentTitle());

        changeBtn.setOnAction(e -> handleChangeBtn());

    }

    public void init() {

    }

    @FXML
    private void handleChangeBtn() {
        System.out.println("change btn clicked...");
        tabController.setChooseTournament();
    }

    public void setTabController(CurrentTournamentTabController r) {
        tabController = r;
    }
}
