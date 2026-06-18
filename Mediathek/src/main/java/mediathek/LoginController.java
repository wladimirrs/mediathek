package mediathek;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.awt.*;
import javafx.event.ActionEvent;

public class LoginController {

    @FXML private Button btnBeenden;
    @FXML private Button btnRegister;
    @FXML private Button btnLogin;
    @FXML private Button btnAusfuehren;

    @FXML private AnchorPane contentPane;
    @FXML private BorderPane mainPane;

    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPasswort;




    @FXML
    void beenden (ActionEvent event) {  // beenden
        System.exit(0);
    }

    @FXML
    void loadRegister (ActionEvent event) { // zur Registrierung
        ViewLoader loader = new ViewLoader();
        Pane view = loader.loadView("RegisterView");
        if (view != null) {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }


    @FXML
    void loadLogin (ActionEvent event) {    // zum Login
        ViewLoader loader = new ViewLoader();
        Pane view = loader.loadView("LoginView");
        if (view != null) {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }


    @FXML
    void loadAusfuehren (ActionEvent event) {
        boolean ok = NutzerDAO.login(txtEmail.getText(), txtPasswort.getText());
        if (ok) {
            ViewLoader loader = new ViewLoader();
            Pane view = loader.loadView("Dashboard");
            mainPane.getChildren().clear();
            mainPane.getChildren().add(view);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setContentText("Benutzername oder Passwort falsch.");
            alert.showAndWait();
        }
    }


}
