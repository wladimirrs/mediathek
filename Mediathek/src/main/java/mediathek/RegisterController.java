package mediathek;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.awt.*;

public class RegisterController {

    @FXML private Button btnRegister;
    @FXML private Button btnLogin;
    @FXML private Button btnBeenden;
    @FXML private Button btnAusfuehren;

    @FXML private AnchorPane contentPane;
    @FXML private BorderPane mainPane;

    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPasswort;





    @FXML
    void beenden (ActiveEvent event) {  // Beenden
        System.exit(0);
    }

    @FXML
    void loadRegister (ActiveEvent event) { // zur Registrierung
        ViewLoader loader = new ViewLoader();
        Pane view = loader.loadView("RegisterView");
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view);
    }

    @FXML
    void loadLogin (ActiveEvent event) {    // zum Login
        ViewLoader loader = new ViewLoader();
        Pane view = loader.loadView("LoginView");
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view);
    }


    @FXML
    void loadAusfuehren (ActiveEvent event) throws Exception {  // Registrierung ausführen
        boolean ok = NutzerDAO.register(txtEmail.getText(), txtPasswort.getText());
        if (ok) {
            ViewLoader loader = new ViewLoader();
            Pane view = loader.loadView("LoginView");
            mainPane.getChildren().clear();
            mainPane.getChildren().add(view);
        }
    }


}
