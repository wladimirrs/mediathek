package Service;

import DAO.ArtikelDAO;
import DAO.BestellungDAO;
import DAO.KundenDAO;
import DAO.TypenDAO;
import Klassen.Artikel;
import Klassen.Bestellung;
import Klassen.Kunde;
import Klassen.Typ;
import mediathek.Observer;
import mediathek.Subject;

import java.util.ArrayList;

public class ModelService implements Subject {


    private final ArrayList<Kunde> kundenListe = new ArrayList<>(); // Attribute
    private final ArrayList<Typ> typenListe = new ArrayList<>();
    private final ArrayList<Artikel> artikelListe = new ArrayList<>();
    private final ArrayList<Bestellung> bestellungListe = new ArrayList<>();
    private final ArrayList<Observer> observerListe = new ArrayList<>();


    public ModelService() {
        kundenListe.addAll(KundenDAO.getAll());
        typenListe.addAll(TypenDAO.getAll());
        artikelListe.addAll(ArtikelDAO.getAll());
        bestellungListe.addAll(BestellungDAO.getAll());
    }




    @Override
    public void entferneObserver(Observer o) {  // Methode des Interface Subject implementiert und überschrieben
        observerListe.remove(o);    // entfernen
    }

    @Override   // hinzufügen
    public void registriereObserver(Observer o) {
        observerListe.add(o);
    }

    @Override
    public void benachrichtigeObserver() {  // bei jedem Observer in der Liste Anzeige updaten
        for (Observer o : observerListe) {
            o.updateView();
        }
    }








    public ArrayList<Kunde> getAlleKunden () {  // Suche
        return kundenListe;
    }
    public ArrayList<Typ> getAlleTypen () {
        return typenListe;
    }
    public ArrayList<Artikel> getAlleArtikel () {
        return artikelListe;
    }
    public ArrayList<Bestellung> getAlleBestellungen () {
        return bestellungListe;
    }




    public void keinfuegen(Kunde k) {   // für Ausleiher
        KundenDAO.insert(k);
        kundenListe.clear();
        kundenListe.addAll(KundenDAO.getAll());
        benachrichtigeObserver();
    }

    public void kaendern (Kunde k) {   // update
        KundenDAO.update(k);
        kundenListe.clear();
        kundenListe.addAll(KundenDAO.getAll());
        benachrichtigeObserver();
    }

    public void kloeschen (int id) { // delete
        KundenDAO.delete(id);
        kundenListe.clear();
        kundenListe.addAll(KundenDAO.getAll());
        benachrichtigeObserver();
    }




    public void teinfuegen(Typ t) { // für Medientypen
        TypenDAO.insert(t);
        typenListe.clear();
        typenListe.addAll(TypenDAO.getAll());
        benachrichtigeObserver();
    }

    public void taendern (Typ t) {   // update
        TypenDAO.update(t);
        typenListe.clear();
        typenListe.addAll(TypenDAO.getAll());
        benachrichtigeObserver();
    }

    public void tloeschen (int id) { // delete
        TypenDAO.delete(id);
        typenListe.clear();
        typenListe.addAll(TypenDAO.getAll());
        benachrichtigeObserver();
    }





    public void aeinfuegen(Artikel a) { // für Sortiment
        ArtikelDAO.insert(a);
        artikelListe.clear();
        artikelListe.addAll(ArtikelDAO.getAll());
        benachrichtigeObserver();
    }

    public void aaendern (Artikel a) {   // update
        ArtikelDAO.update(a);
        artikelListe.clear();
        artikelListe.addAll(ArtikelDAO.getAll());
        benachrichtigeObserver();
    }

    public void aloeschen (int id) { // delete
        ArtikelDAO.delete(id);
        artikelListe.clear();
        artikelListe.addAll(ArtikelDAO.getAll());
        benachrichtigeObserver();
    }



    public void beinfuegen(Bestellung b) {  // für Ausleihen
        BestellungDAO.insert(b);
        bestellungListe.clear();
        bestellungListe.addAll(BestellungDAO.getAll());
        benachrichtigeObserver();
    }

    public void baendern (Bestellung b) {   // update
        BestellungDAO.update(b);
        bestellungListe.clear();
        bestellungListe.addAll(BestellungDAO.getAll());
        benachrichtigeObserver();
    }

    public void bloeschen (int id) { // delete
        BestellungDAO.delete(id);
        bestellungListe.clear();
        bestellungListe.addAll(BestellungDAO.getAll());
        benachrichtigeObserver();
    }






}
