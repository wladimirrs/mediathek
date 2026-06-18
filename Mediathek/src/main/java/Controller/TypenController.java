package Controller;

import DAO.TypenDAO;
import Klassen.Typ;
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

public class TypenController implements Observer {

    @FXML private Button btnAendern;
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe;
    @FXML private TextField txtId;
    @FXML private TextField txtBezeichnung;
    @FXML private TextField txtAusleihdauer;
    @FXML private TextField txtUmfang;

    @FXML private TableView<Typ> tblTyp;
    @FXML private TableColumn<Typ, Integer> colId;
    @FXML private TableColumn<Typ, String> colBezeichnung;
    @FXML private TableColumn<Typ, Integer> colAusleihdauer;
    @FXML private TableColumn<Typ, Integer> colUmfang;


    private ObservableList<Typ> daten;
    private final ModelService service = new ModelService();
    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colBezeichnung.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBezeichnung()));
        colAusleihdauer.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAusleihdauer()).asObject());
        colUmfang.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getUmfang()).asObject());
        service.registriereObserver(this);
        daten = FXCollections.observableArrayList(TypenDAO.getAll());
        tblTyp.setItems(daten);
    }

    @Override
    public void updateView() {
        daten.setAll(service.getAlleTypen());
        tblTyp.refresh();
    }


    @FXML
    void einfuegen(ActionEvent event) { // insert
        Typ t = new  Typ(
                txtBezeichnung.getText(),
                Integer.parseInt(txtAusleihdauer.getText()),
                Integer.parseInt(txtUmfang.getText())
        );
        service.einfuegen(t);
    }

    @FXML
    void loeschen(ActionEvent event) {              // gewählten Datensatz löschen
        Typ selected = tblTyp.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.loeschen(selected.getId());
        }
    }

    @FXML
    void aendern(ActionEvent event) {   // update
        Typ t = new Typ(
                Integer.parseInt(txtId.getText()),
                txtBezeichnung.getText(),
                Integer.parseInt(txtAusleihdauer.getText()),
                Integer.parseInt(txtUmfang.getText())
        );
        service.aendern(t);
    }

    @FXML
    void suchen(ActionEvent event) {    // Suche
        String query = txtEingabe.getText().toLowerCase();
        if (query.isBlank()) {
            tblTyp.setItems(daten);
            return;
        }
        ObservableList<Typ> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(m ->
                                (m.getId() != 0 && String.valueOf(m.getId()).contains(query)) ||
                                        (m.getBezeichnung() != null && m.getBezeichnung().toLowerCase().contains(query)) ||
                                        (m.getAusleihdauer() != 0 && String.valueOf(m.getAusleihdauer()).contains(query)) ||
                                        (m.getUmfang() != 0 && String.valueOf(m.getUmfang()).contains(query))                        )
                        .toList()
        );
        tblTyp.setItems(gefiltert);
    }

}
