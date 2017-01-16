/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminapp;

import java.io.IOException;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import adminapp.model.CurrentTournament;
import adminapp.model.Dictionary;
import serializable.model.Tournament;
import serializable.model.Serializator;
import adminapp.presentation.RootLayoutController;
import javafx.scene.image.Image;

/**
 *
 * @author Emilia
 */
public class Main extends Application {

    private Stage primaryStage;
    public BorderPane rootLayout;
    private RootLayoutController mainController;

    public static void main(String[] args) throws IOException {

        Application.launch(Main.class, (java.lang.String[]) null);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Internationalization        
        new Dictionary();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(Dictionary.getString("window-title"));

        this.primaryStage.getIcons().add(new Image("file:W:/Netbeans Projects/AdminApp/src/adminapp/presentation/resources/images/ico128.png"));

        //showChartMaker();
//        Tournament t = new Tournament("Zawody testowe", new Date(2017,2,15));
//        t.init();
//        Serializator.writeToFile(t, "tournaments/tournament1");
        initBoard(0);
        initRootLayout();
    }

    public void initRootLayout() {

        try {

            Font.loadFont(Main.class.getResource("presentation/resources/fonts/Toxia_FRE.ttf").toExternalForm(), 10);
            Font.loadFont(Main.class.getResource("presentation/resources/fonts/phagspa.ttf").toExternalForm(), 10);
            Font.loadFont(Main.class.getResource("presentation/resources/fonts/phagspab.ttf").toExternalForm(), 10);
            Font.loadFont(Main.class.getResource("presentation/resources/fonts/fontawesome-webfont.ttf").toExternalForm(), 10);

            // Load root layout from fxml file.            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("presentation/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            mainController = loader.getController();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            mainController.setPrimaryStage(primaryStage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initBoard(Integer boardId) {
//        Tournament t = new Tournament("tytuł", new Date(2016,12,12));
//        t.init();
//        Serializator.writeToFile(t,"tournaments/tournament3");
        //  CurrentTournament.setTournament((Tournament) Serializator.readFromFile("tournaments/t_2016-06-20"));

        // mainController.init();
    }

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        launch(args);
//
//    }
}
