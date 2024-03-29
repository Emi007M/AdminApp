package adminapp.presentation.currentTournamentTab.views;

import adminapp.model.CurrentTournament;
import adminapp.model.Dictionary;
import adminapp.presentation.ViewBaseController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static javafx.application.Platform.runLater;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Scale;
import serializable.model.Competition;

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

            Text title = new Text(c.getID().toString() + ". " + c.getTitle());

            Text descr = new Text(c.getDescr());

            Text competitors = new Text(Dictionary.getString("t2.contestants")+": " + c.getPlayersAmount().toString() + "     ");
            JFXButton seeChart = new JFXButton(Dictionary.getString("t2.print-brackets"));
            seeChart.setOnAction(e -> handleSeeChartBtn(c.getID()));
            TextFlow t2 = new TextFlow(competitors, seeChart);

            VBox container = new VBox(title, descr, t2);

            title.getStyleClass().add("startingList-top");
            descr.getStyleClass().add("startingList-middle");
            competitors.getStyleClass().add("startingList-bottom");
            seeChart.getStyleClass().add("startingList-bottom");
            container.getStyleClass().add("startingList-container");

            tList.add(container);

        }

        listsBox.getChildren().addAll(tList);
    }

    private void handleSeeChartBtn(Integer id) {
        System.out.println("clicked see chart btn for competition " + id);

        Competition tmp = CurrentTournament.getCompetition(id);
        ArrayList<serializable.model.Node> matches = tmp.getChart().getBracketMatches();
        if (matches.size() == 0) {
            tmp.initChart();
            matches = tmp.getChart().getBracketMatches();
        }

        String title = Dictionary.getString("t2.starting-list")+" " + tmp.getID() + ". " + tmp.getTitle();

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
