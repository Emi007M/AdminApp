package adminapp.presentation.currentTournamentTab.views;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import serializable.model.Competition;
import adminapp.model.CurrentTournament;
import adminapp.presentation.ViewBaseController;
import com.jfoenix.controls.JFXToggleButton;
import static javafx.application.Platform.runLater;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author Emilia
 */
public class StartingListsController extends ViewBaseController {

    @FXML
    private VBox listsBox;

    @FXML
    private JFXToggleButton blockedTglBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBackBtn();

        updateLists();
    }

    private void updateLists() {

        ArrayList<Node> tList = new ArrayList<>();

        for (Competition c : CurrentTournament.getTournamentCompetitions()) {
            Text id = new Text(c.getID().toString());
            Text title = new Text(c.getTitle());
            Text descr = new Text(c.getDescr());
            Text competitors = new Text(c.getPlayersAmount().toString());

            TextFlow t = new TextFlow(id, title, descr, competitors);
            Button seeChart = new Button("see chart");
            seeChart.setOnAction(e -> handleSeeChartBtn(c.getID()));
            t.getChildren().add(seeChart);

            tList.add(t);

        }

        listsBox.getChildren().addAll(tList);
    }

    private void handleSeeChartBtn(Integer id) {
        System.out.println("clicked see chart btn for competition " + id);

        Competition tmp = CurrentTournament.getCompetition(id);
        ArrayList<serializable.model.Node> matches = tmp.getChart().getBracketMatches();

        String title = "Lista startowa nr "+tmp.getID()+". "+tmp.getTitle();

        BracketView chart = new BracketView(matches, title);

        chart.getStylesheets().add("adminapp/presentation/currentTournamentTab/views/style.css");

        //listsBox.getChildren().add(chart);

        print(chart);
    }

    public void print(Node node) {

        runLater(new Runnable() {
            public void run() {
                PrinterJob job = PrinterJob.createPrinterJob();
                if (job != null && job.showPrintDialog(null)) {

                    PageLayout pageLayout = job.getPrinter().getDefaultPageLayout();
                    double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
                    double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
                    double minimumScale = Math.min(scaleX, scaleY);
                    Scale scale = new Scale(minimumScale, minimumScale);

                    try {
                        node.getTransforms().add(scale);

                        boolean success = job.printPage(node);
                        if (success) {
                            job.endJob();
                        }

                    } finally {
                        node.getTransforms().remove(scale);

                    }
                }
            }
        });
    }

}
