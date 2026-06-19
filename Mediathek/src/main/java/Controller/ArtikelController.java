package Controller;

import DAO.ArtikelDAO;
import DAO.TypenDAO;
import Klassen.Artikel;
import Klassen.Typ;
import Service.ModelService;
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

import java.awt.*;
import java.awt.Button;

public class ArtikelController implements Observer {

    @FXML private Button btnAendern;
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe;
    @FXML private TextField txtId;
    @FXML private ChoiceBox<Typ> cbTyp;
    @FXML private TextField txtTitel;
    @FXML private TextField txtAbachtzehn;
    @FXML private TextField txtGenre;
    @FXML private TextField txtUmfang;

    @FXML private TableView<Artikel> tblArtikel;
    @FXML private TableColumn<Artikel, Integer> colId;
    @FXML private TableColumn<Artikel, Typ> colTyp;
    @FXML private TableColumn<Artikel, String> colTitel;
    @FXML private TableColumn<Artikel, Boolean> colAbachtzehn;
    @FXML private TableColumn<Artikel, String> colGenre;
    @FXML private TableColumn<Artikel, Integer> colUmfang;


    private ObservableList<Artikel> daten;
    private final ModelService service = new ModelService();
    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colTyp.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getTyp()));
        colTitel.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitel()));
        colAbachtzehn.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().isAbachtzehn()));
        colAbachtzehn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : (item ? "ab 18" : "-"));
            }
        });
        colGenre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGenre()));
        colUmfang.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getUmfang()).asObject());
        colUmfang.setCellFactory(column -> new TableCell<Artikel, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                    return;
                }
                Artikel artikel = getTableRow().getItem();
                String typ = String.valueOf(artikel.getTyp());
                String ufg = String.valueOf(artikel.getUmfang());
                if ((typ.equalsIgnoreCase("Film") || typ.equalsIgnoreCase("Brettspiel")) && (ufg.endsWith("01") || ufg.equalsIgnoreCase("1"))) {
                    setText(item + " Minute");
                } else if (typ.equalsIgnoreCase("Film") || typ.equalsIgnoreCase("Brettspiel")) {
                    setText(item + " Minuten");
                } else if (typ.equalsIgnoreCase("Buch") && (ufg.endsWith("01") || ufg.equalsIgnoreCase("1"))) {
                    setText(item + " Seite");
                } else if (typ.equalsIgnoreCase("Buch")) {
                    setText(item + " Seiten");
                } else if (typ.equalsIgnoreCase("Videospiel") &&  (ufg.endsWith("01") || ufg.equalsIgnoreCase("1"))) {
                    setText(item + " Stunde");
                } else if (typ.equalsIgnoreCase("Videospiel")) {
                    setText(item + " Stunden");
                } else if (ufg.endsWith("01") || ufg.equalsIgnoreCase("1")) {
                    setText(item + " Minute");
                } else {
                    setText(item + " Minuten");
                }
            }
        });
        cbTyp.setItems(FXCollections.observableArrayList(TypenDAO.getAll()));
        service.registriereObserver(this);
        daten = FXCollections.observableArrayList(ArtikelDAO.getAll());
        tblArtikel.setItems(daten);
    }

    @Override
    public void updateView() {
        daten.setAll(service.getAlleArtikel());
        tblArtikel.refresh();
    }


    @FXML
    void einfuegen(ActionEvent event) { // insert
        Artikel a = new  Artikel(
                cbTyp.getValue(),
                txtTitel.getText(),
                Boolean.parseBoolean(txtAbachtzehn.getText()),
                txtGenre.getText(),
                Integer.parseInt(txtUmfang.getText())
        );
        service.aeinfuegen(a);
    }

    @FXML
    void loeschen(ActionEvent event) {              // gewählten Datensatz löschen
        Artikel selected = tblArtikel.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.aloeschen(selected.getId());
        }
    }

    @FXML
    void aendern(ActionEvent event) {   // update
        Artikel a = new Artikel(
                Integer.parseInt(txtId.getText()),
                cbTyp.getValue(),
                txtTitel.getText(),
                Boolean.parseBoolean(txtAbachtzehn.getText()),
                txtGenre.getText(),
                Integer.parseInt(txtUmfang.getText())
        );
        service.aaendern(a);
    }

    @FXML
    void suchen(ActionEvent event) {    // Suche
        String query = txtEingabe.getText().toLowerCase();
        if (query.isBlank()) {
            tblArtikel.setItems(daten);
            return;
        }
        ObservableList<Artikel> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(m ->
                                (m.getId() != 0 && String.valueOf(m.getId()).contains(query)) ||
                                        (m.getTyp() != null && String.valueOf(m.getTyp()).contains(query)) ||
                                        (m.getTitel() != null && m.getTitel().toLowerCase().contains(query)) ||
                                        (String.valueOf(m.isAbachtzehn()).contains(query)) ||
                                        (m.getGenre() != null && m.getGenre().toLowerCase().contains(query)) ||
                                        (m.getUmfang() != 0 && String.valueOf(m.getUmfang()).contains(query))
                        )
                        .toList()
        );
        tblArtikel.setItems(gefiltert);
    }

}
