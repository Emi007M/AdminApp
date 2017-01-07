package adminapp.presentation;

import adminapp.presentation.currentTournamentTab.*;
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
public abstract class ViewBaseController implements Initializable {


    @FXML
    protected FlowPane mainFlow;
    
    protected LayoutBaseController tabController;
    
    @FXML
    protected Button gobackBtn;

    @Override
    abstract public void initialize(URL location, ResourceBundle resources);

    public void init() {

    }

   
    
    /**
     * has to be overriden in Button Views
     * @param r 
     */
    public void setTabController(LayoutBaseController r) {
        tabController = r;
    }
    
    protected void setBackBtn(){
        gobackBtn.setOnAction(e->tabController.init());
    }
}
