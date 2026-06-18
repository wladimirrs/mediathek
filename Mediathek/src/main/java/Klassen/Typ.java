package Klassen;

public class Typ {

    private int id; // Attribute
    private String bezeichnung;
    private int ausleihdauer;


    public Typ(int id, String bezeichnung, int ausleihdauer) {    // Konstruktoren
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.ausleihdauer = ausleihdauer;
    }

    public Typ(String bezeichnung, int ausleihdauer) {
        this.bezeichnung = bezeichnung;
        this.ausleihdauer = ausleihdauer;
    }

    public Typ(String bezeichnung) {    // Konstruktoren
        this.bezeichnung = bezeichnung;
    }

    public Typ() {}





    public int getId() {    // getter und setter
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public int getAusleihdauer() { return  ausleihdauer; }

    public void setId(int id) {
        this.id = id;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setAusleihdauer(int ausleihdauer) {this.ausleihdauer = ausleihdauer; }

    @Override
    public String toString() {
        return bezeichnung;
    }
}
