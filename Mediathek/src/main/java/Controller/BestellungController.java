package Controller;

import DAO.BestellungDAO;
import Klassen.Artikel;
import Klassen.Bestellung;
import Klassen.Kunde;
import Klassen.Typ;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import mediathek.Observer;

import java.awt.Button;

public class BestellungController implements Observer {

    @FXML private Button btnAendern;
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe;
    @FXML private TextField txtId;
    @FXML private ChoiceBox<Kunde> cbKundenId;
    @FXML private ChoiceBox<Artikel> cbArtikelId;
    @FXML private TextField txtVon;
    @FXML private TextField txtBis;

    @FXML private TableView<Bestellung> tblBestellung;
    @FXML private TableColumn<Bestellung, Integer> colId;
    @FXML private TableColumn<Bestellung, Kunde> colKundenId;
    @FXML private TableColumn<Bestellung, Artikel> colArtikelId;
    @FXML private TableColumn<Bestellung, String> colVon;
    @FXML private TableColumn<Bestellung, String> colBis;


    private ObservableList<Bestellung> daten;
    private final ModelService service = new ModelService();
    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colKundenId.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getKunde()));
        colArtikelId.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getArtikel()));
        colVon.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVon()));
        colBis.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBis()));
        service.registriereObserver(this);
        daten = FXCollections.observableArrayList(BestellungDAO.getAll());
        tblBestellung.setItems(daten);
    }

    @Override
    public void updateView() {
        daten.setAll(service.getAlleBestellungen());
        tblBestellung.refresh();
    }


    @FXML
    void einfuegen(ActionEvent event) { // insert
        Bestellung b = new  Bestellung(
                cbKundenId.getValue(),
                cbArtikelId.getValue(),
                txtVon.getText(),
                txtBis.getText()
        );
        service.einfuegen(b);
    }

    @FXML
    void loeschen(ActionEvent event) {              // gewählten Datensatz löschen
        Bestellung selected = tblBestellung.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.loeschen(selected.getId());
        }
    }

    @FXML
    void aendern(ActionEvent event) {   // update
        Bestellung b = new Bestellung(
                Integer.parseInt(txtId.getText()),
                cbKundenId.getValue(),
                cbArtikelId.getValue(),
                txtVon.getText(),
                txtBis.getText()
        );
        service.aendern(b);
    }

    @FXML
    void suchen(ActionEvent event) {    // Suche
        String query = txtEingabe.getText().toLowerCase();
        if (query.isBlank()) {
            tblBestellung.setItems(daten);
            return;
        }
        ObservableList<Bestellung> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(m ->
                                (m.getId() != 0 && String.valueOf(m.getId()).contains(query)) ||
                                        (m.getKunde() != null && String.valueOf(m.getKunde()).contains(query)) ||
                                        (m.getArtikel() != null && String.valueOf(m.getArtikel()).contains(query)) ||
                                        (m.getVon() != null && m.getVon().toLowerCase().contains(query)) ||
                                        (m.getBis() != null && m.getBis().toLowerCase().contains(query))
                        )
                        .toList()
        );
        tblBestellung.setItems(gefiltert);
    }

}
