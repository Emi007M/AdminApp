package adminapp.presentation.currentTournamentTab.views;

import adminapp.model.CurrentTournament;
import adminapp.presentation.ViewBaseController;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import serializable.model.data.Serializator;
import serializable.model.Tournament;

/**
 *
 * @author Emilia
 */
public class ChooseTournamentController extends ViewBaseController {

    @FXML
    private VBox VBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBackBtn();
    }

    public void updateList() {
        //get tournaments from folder
        ArrayList<Serializable> objs;
        objs = Serializator.readAllFromFolder("tournaments");

        //print tournaments
        for (Serializable s : objs) {
            Tournament t = (Tournament) s;

            Button btn = new Button("(" + t.getDate() + ") " + t.getTitle());
            btn.getStyleClass().add("tournamentBtn");
            if (CurrentTournament.getTournament() != null
                    && t.getDate().equals(CurrentTournament.getTournament().getDate())
                    && t.getTitle().equals(CurrentTournament.getTournament().getTitle())) {
                btn.getStyleClass().add("current");
            }
            btn.setOnAction(e -> setTournament(t));

            VBox.getChildren().add(btn);

        }
    }

    private void setTournament(Tournament t) {
        CurrentTournament.setTournament(t);
        tabController.init();
    }

}
