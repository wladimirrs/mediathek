package Controller;

import DAO.KundenDAO;
import Klassen.Kunde;
import Service.ModelService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import mediathek.Observer;

import java.awt.*;
import java.awt.Button;

public class KundenController implements Observer {

    @FXML private Button btnAendern;
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe;
    @FXML private TextField txtId;
    @FXML private TextField txtNachname;
    @FXML private TextField txtVorname;
    @FXML private TextField txtMediatheknummer;
    @FXML private TextField txtStrasse;
    @FXML private TextField txtHausnummer;
    @FXML private TextField txtPlz;
    @FXML private TextField txtOrt;
    @FXML private TextField txtGeburtsdatum;

    @FXML private TableView<Kunde> tblKunde;
    @FXML private TableColumn<Kunde, Integer> colId;
    @FXML private TableColumn<Kunde, String> colNachname;
    @FXML private TableColumn<Kunde, String> colVorname;
    @FXML private TableColumn<Kunde, String> colMediatheknummer;
    @FXML private TableColumn<Kunde, String> colStrasse;
    @FXML private TableColumn<Kunde, String> colHausnummer;
    @FXML private TableColumn<Kunde, String> colPlz;
    @FXML private TableColumn<Kunde, String> colOrt;
    @FXML private TableColumn<Kunde, String> colGeburtsdatum;


    private ObservableList<Kunde> daten;
    private final ModelService service = new ModelService();
    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colNachname.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNachname()));
        colVorname.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVorname()));
        colMediatheknummer.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMediatheknummer()));
        colStrasse.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStrasse()));
        colHausnummer.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHausnummer()));
        colPlz.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPlz()));
        colOrt.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrt()));
        colGeburtsdatum.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGeburtsdatum()));
        service.registriereObserver(this);
        daten = FXCollections.observableArrayList(KundenDAO.getAll());
        tblKunde.setItems(daten);
    }

    @Override
    public void updateView() {
        daten.setAll(service.getAlleKunden());
        tblKunde.refresh();
    }


    @FXML
    void einfuegen(ActionEvent event) { // insert
        Kunde k = new  Kunde(
                txtNachname.getText(),
                txtVorname.getText(),
                txtMediatheknummer.getText(),
                txtStrasse.getText(),
                txtHausnummer.getText(),
                txtPlz.getText(),
                txtOrt.getText(),
                txtGeburtsdatum.getText()
        );
        service.keinfuegen(k);
    }

    @FXML
    void loeschen(ActionEvent event) {              // gewählten Datensatz löschen
        Kunde selected = tblKunde.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.kloeschen(selected.getId());
        }
    }

    @FXML
    void aendern(ActionEvent event) {   // update
        Kunde k = new Kunde(
                Integer.parseInt(txtId.getText()),
                txtNachname.getText(),
                txtVorname.getText(),
                txtMediatheknummer.getText(),
                txtStrasse.getText(),
                txtHausnummer.getText(),
                txtPlz.getText(),
                txtOrt.getText(),
                txtGeburtsdatum.getText()
        );
        service.kaendern(k);
    }

    @FXML
    void suchen(ActionEvent event) {    // Suche
        String query = txtEingabe.getText().toLowerCase();
        if (query.isBlank()) {
            tblKunde.setItems(daten);
            return;
        }
        ObservableList<Kunde> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(m ->
                                (m.getId() != 0 && String.valueOf(m.getId()).contains(query)) ||
                                        (m.getNachname() != null && m.getNachname().toLowerCase().contains(query)) ||
                                        (m.getVorname() != null && m.getVorname().toLowerCase().contains(query)) ||
                                        (m.getMediatheknummer() != null && m.getMediatheknummer().toLowerCase().contains(query)) ||
                                        (m.getStrasse() != null && m.getStrasse().toLowerCase().contains(query)) ||
                                        (m.getHausnummer() != null && m.getHausnummer().toLowerCase().contains(query)) ||
                                        (m.getPlz() != null && m.getPlz().toLowerCase().contains(query)) ||
                                        (m.getOrt() != null && m.getOrt().toLowerCase().contains(query)) ||
                                        (m.getGeburtsdatum() != null && m.getGeburtsdatum().toLowerCase().contains(query))
                        )
                        .toList()
        );
        tblKunde.setItems(gefiltert);
    }

}
