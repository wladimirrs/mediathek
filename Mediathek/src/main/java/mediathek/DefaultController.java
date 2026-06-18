package mediathek;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import javafx.event.ActionEvent;

public class DefaultController {

    @FXML private Button btnRegister;
    @FXML private Button btnLogin;
    @FXML private Button btnBeenden;

    @FXML private AnchorPane contentPane;
    @FXML private BorderPane mainPane;




    @FXML
    void beenden(ActionEvent event) {   // Beenden
        System.exit(0);
    }

    @FXML
    void loadRegister (ActionEvent event) { // zur Registrierung
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("RegisterView");
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view);
    }

    @FXML
    void loadLogin (ActionEvent event) {    // zum Login
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("LoginView");
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view);
    }


}
