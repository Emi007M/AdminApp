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

//            JFXButton seeChart = new JFXButton("PRINT BRACKETS");
//            seeChart.setOnAction(e -> handleSeeChartBtn(c.getID()));
//            TextFlow t2 = new TextFlow(competitors, seeChart);
            VBox container = new VBox(title);
            container.getChildren().addAll(results_t);

            title.getStyleClass().add("startingList-top");
            container.getStyleClass().add("startingList-container");

            tList.add(container);

        }

        listsBox.getChildren().setAll(tList);
    }
//
//    private void handleSeeChartBtn(Integer id) {
//        System.out.println("clicked see chart btn for competition " + id);
//
//        Competition tmp = CurrentTournament.getCompetition(id);
//        ArrayList<serializable.model.Node> matches = tmp.getChart().getBracketMatches();
//        if(matches.size()==0){
//            tmp.initChart();
//            matches = tmp.getChart().getBracketMatches();
//        }
//
//        String title = "Lista startowa nr "+tmp.getID()+". "+tmp.getTitle();
//
//        BracketView chart = new BracketView(matches, title);
//
//        chart.getStylesheets().add("adminapp/presentation/currentTournamentTab/views/style.css");
//
//        //listsBox.getChildren().add(chart);
//
//        print(chart);
//    }
//
//    public void print(Node node) {
//
//        runLater(new Runnable() {
//            public void run() {
//                PrinterJob job = PrinterJob.createPrinterJob();
//                if (job != null && job.showPrintDialog(null)) {
//
//                    PageLayout pageLayout = job.getPrinter().getDefaultPageLayout();
//                    double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
//                    double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
//                    double minimumScale = Math.min(scaleX, scaleY);
//                    Scale scale = new Scale(minimumScale, minimumScale);
//
//                    try {
//                        node.getTransforms().add(scale);
//
//                        boolean success = job.printPage(node);
//                        if (success) {
//                            job.endJob();
//                        }
//
//                    } finally {
//                        node.getTransforms().remove(scale);
//
//                    }
//                }
//            }
//        });
//    }
//
}