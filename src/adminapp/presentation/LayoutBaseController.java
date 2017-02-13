package adminapp.presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Emilia
 */
public abstract class LayoutBaseController implements Initializable {

    @FXML
    protected FlowPane mainFlow;

    protected RootLayoutController rootController;

    @Override
    abstract public void initialize(URL location, ResourceBundle resources);

    abstract public void init();

    public void setRootController(RootLayoutController r) {
        rootController = r;
    }

    public RootLayoutController getRootLayoutController() {
        return rootController;
    }

}
