package adminapp.presentation.currentTournamentTab.views;

import adminapp.model.CurrentTournament;
import adminapp.presentation.ViewBaseController;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import serializable.model.Competition;

/**
 *
 * @author Emilia
 */
public class ManageBoardsController extends ViewBaseController {

    @FXML
    private VBox box0, box1, box2, box3, box4;

    @FXML
    private Button saveAllBtn;

    private static final DataFormat competitionDataFormat = new DataFormat("src.seriaizable.model.Competition") {
    };

    private ArrayList<LabelWithCompetition> labels;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBackBtn();

    }

    public void init() {

        box0.getChildren().clear();
        box1.getChildren().clear();
        box2.getChildren().clear();
        box3.getChildren().clear();
        box4.getChildren().clear();

        //generate all lists
        labels = new ArrayList<>();

        for (Competition c : CurrentTournament.getTournamentCompetitions()) {

            LabelWithCompetition l = new LabelWithCompetition();

            l.setOnDragDetected(e -> handleDragOver(l));
            l.setOnDragDone(e -> handleDragDone(e, l));

            l.setCompetition(c);
            labels.add(l);
        }

        //place lists
        for (LabelWithCompetition l : labels) {
            switch (l.getCompetition().getBoardID()) {

                case 1:
                    box1.getChildren().add(l);
                    break;
                case 2:
                    box2.getChildren().add(l);
                    break;
                case 3:
                    box3.getChildren().add(l);
                    break;
                case 4:
                    box4.getChildren().add(l);
                    break;
                default:
                    box0.getChildren().add(l);
            }
        }

        //set drop areas
        box0.setOnDragOver(e -> handleDrop(e, box0));
        box0.setOnDragDropped(e -> handleDropped(e, box0));

        box1.setOnDragOver(e -> handleDrop(e, box1));
        box1.setOnDragDropped(e -> handleDropped(e, box1));

        box2.setOnDragOver(e -> handleDrop(e, box2));
        box2.setOnDragDropped(e -> handleDropped(e, box2));

        box3.setOnDragOver(e -> handleDrop(e, box3));
        box3.setOnDragDropped(e -> handleDropped(e, box3));

        box4.setOnDragOver(e -> handleDrop(e, box4));
        box4.setOnDragDropped(e -> handleDropped(e, box4));

    }

    private void handleDragOver(LabelWithCompetition l) {
        /* drag was detected, start a drag-and-drop gesture*/
 /* allow any transfer mode */
        Dragboard db = l.startDragAndDrop(TransferMode.ANY);

        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();

        //content.putString(l.getText());
        Competition c = l.getCompetition();
        content.put(competitionDataFormat, c);
        db.setContent(content);

        //event.consume();
    }

    private void handleDrop(DragEvent event, VBox target) {
        /* data is dragged over the target */
 /* accept it only if it is not dragged from the same node 
         * and if it has a string data */
        if (event.getGestureSource() != target
                && event.getDragboard().hasContent(competitionDataFormat)) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    public void handleDropped(DragEvent event, VBox target) {
        /* data dropped */
 /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasContent(competitionDataFormat)) {

            //add label with competition and update competition
            Competition c = (Competition) db.getContent(competitionDataFormat);
            LabelWithCompetition l = new LabelWithCompetition();
            l.setCompetition(c);
            Integer boxID = Character.getNumericValue(target.getId().charAt(3));

            System.out.println("box is " + target.getId().charAt(3) + ", id=" + boxID);
            c.setBoardID(boxID);
            labels.add(l);

            target.getChildren().add(l);
            l.setOnDragDetected(e -> handleDragOver(l));
            l.setOnDragDone(e -> handleDragDone(e, l));

            success = true;
        }
        /* let the source know whether the string was successfully 
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    public void handleDragDone(DragEvent event, LabelWithCompetition source) {
        /* the drag and drop gesture ended */
 /* if the data was successfully moved, clear it */
        if (event.getTransferMode() == TransferMode.MOVE) {
            box0.getChildren().remove(source);
            box1.getChildren().remove(source);
            box2.getChildren().remove(source);
            box3.getChildren().remove(source);
            box4.getChildren().remove(source);

        }
        event.consume();
    }

    @FXML
    public void handleSaveChangesBtn() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Saving changes");
        alert.setHeaderText("Saving changes");
        alert.setContentText(" Are you sure, you want to save all the changes?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            for (LabelWithCompetition l : labels) {
                CurrentTournament.getCompetition(
                        l.getCompetition().getID())
                        .setBoardID(l.getCompetition().getBoardID());
            }
            CurrentTournament.getTournament().saveToFile();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    /**
     * Auxiliary extended Label class which stores an additional Competition
     * object
     */
    class LabelWithCompetition extends Label {

        private Competition competition;

        private LabelWithCompetition(String string) {
            super(string);
        }

        private LabelWithCompetition() {
        }

        public Competition getCompetition() {
            return competition;
        }

        public void setCompetition(Competition competition) {
            this.competition = competition;
            if (competition.isFinished()) {
                this.setTextFill(Paint.valueOf("GREEN"));
            }
            if (competition.isLocked()) {
                this.setTextFill(Paint.valueOf("GREY"));
            }

            this.setText(competition.getID() + ". " + competition.getTitle());

        }

    }
}
