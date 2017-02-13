package adminapp.presentation.currentTournamentTab.views;

import adminapp.model.CurrentTournament;
import adminapp.presentation.ViewBaseController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import serializable.model.Competition;

/**
 *
 * @author Emilia
 */
public class ResultsController extends ViewBaseController {

    @FXML
    private VBox listsBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBackBtn();

        updateLists();
    }

    public void updateLists() {

        ArrayList<Node> tList = new ArrayList<>();

        System.out.println("Results updating");

        for (Competition c : CurrentTournament.getTournamentCompetitions()) {
            if (!c.isFinished()) {
                continue;
            }

            if (!c.hasResults()) {
                c.setResults();
            }

            Text title = new Text(c.getID().toString() + ". " + c.getTitle());

            ArrayList<String> results = c.getResults();
            ArrayList<Label> results_t = new ArrayList<>();
            for (String s : results) {
                results_t.add(new Label(s));
            }


            VBox container = new VBox(title);
            container.getChildren().addAll(results_t);

            title.getStyleClass().add("startingList-top");
            container.getStyleClass().add("startingList-container");

            tList.add(container);

        }

        listsBox.getChildren().setAll(tList);
    }

}
