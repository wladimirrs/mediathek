package mediathek;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.awt.*;
import javafx.event.ActionEvent;

public class DashboardController {

    @FXML private Button btnBeenden;
    @FXML private Button btnLogout;
    @FXML private Button btnHome;
    @FXML private Button btnKunde;
    @FXML private Button btnTyp;
    @FXML private Button btnArtikel;
    @FXML private Button btnBestellung;

    @FXML private AnchorPane contentPane;
    @FXML private BorderPane mainPane;




    @FXML
    void beenden(ActionEvent event) {   // beenden
        System.exit(0);
    }

    @FXML
    void loadLogout(ActionEvent event) {    // ausloggen
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("DefaultView");
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view);

        AnchorPane.setTopAnchor(view, 0.0);
        AnchorPane.setBottomAnchor(view, 0.0);
        AnchorPane.setLeftAnchor(view, 0.0);
        AnchorPane.setRightAnchor(view, 0.0);
    }



    @FXML
    void loadHome(ActionEvent event) {  // Home
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("Dashboard");
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view);
    }

    @FXML
    void loadKunde(ActionEvent event) { // Kundentabelle
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("KundeView");
        if (view != null) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }

    @FXML
    void loadTyp(ActionEvent event) {   // Typentabelle
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("TypView");
        if (view != null) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }

    @FXML
    void loadArtikel(ActionEvent event) {   // Artikeltabelle
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("ArtikelView");
        if (view != null) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }

    @FXML
    void loadBestellung(ActionEvent event) {    // Ausleihhistorie
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("BestellungView");
        if (view != null) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }


}
