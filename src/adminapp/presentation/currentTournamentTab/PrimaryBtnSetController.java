package adminapp.presentation.currentTournamentTab;

import adminapp.presentation.ViewBaseController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author Emilia
 */
public class PrimaryBtnSetController extends ViewBaseController {

    @FXML
    private Button contestantsBtn, listsBtn, boardsBtn, resultsBtn, changeBtn;

    private CurrentTournamentTabController tabController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  titleLabel.setText(CurrentTournament.getTournamentTitle());
        //  setPrimaryBtnSet();

        contestantsBtn.setOnAction(e -> handleContestantsBtn());
        listsBtn.setOnAction(e -> handleListsBtn());
        boardsBtn.setOnAction(e -> handleBoardsBtn());
        resultsBtn.setOnAction(e -> handleResultsBtn());
        changeBtn.setOnAction(e -> handleChangeBtn());

    }

    public void init() {

    }

    @FXML
    private void handleContestantsBtn() {
        System.out.println("contestants btn clicked...");
        tabController.setContestants();
    }

    @FXML
    private void handleListsBtn() {
        System.out.println("lists btn clicked...");
        tabController.setStartingLists();

    }

    @FXML
    private void handleBoardsBtn() {
        System.out.println("boards btn clicked...");
        tabController.setManageBoards();
    }

    @FXML
    private void handleResultsBtn() {
        System.out.println("results btn clicked...");
        tabController.setResults();
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
