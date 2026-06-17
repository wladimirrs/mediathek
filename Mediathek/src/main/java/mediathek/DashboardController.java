package mediathek;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.awt.event.ActionEvent;

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
    void logout(ActionEvent event) {    // ausloggen
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("DefaultView");
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view);
    }




    void loadHome(ActionEvent event) {  // Home
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("Dashboard");
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view);
    }

    void loadKunde(ActionEvent event) { // Kundentabelle
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("KundeView");
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view);
    }

    void loadTyp(ActionEvent event) {   // Typentabelle
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("TypView");
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view);
    }

    void loadArtikel(ActionEvent event) {   // Artikeltabelle
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("ArtikelView");
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view);
    }

    void loadBestellung(ActionEvent event) {    // Ausleihhistorie
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("BestellungView");
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view);
    }


}
