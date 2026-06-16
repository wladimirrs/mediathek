package Klassen;

public class Typ {

    private int id; // Attribute
    private String bezeichnung;
    private int ausleihdauer;
    private int umfang;


    public Typ(int id, String bezeichnung) {    // Konstruktoren
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    public Typ(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Typ() {}





    public int getId() {    // getter und setter
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }


    @Override
    public String toString() {
        if (id == 1) {
            return bezeichnung + ", " + umfang + " Minuten";
        } else if (id == 2) {
            return bezeichnung + ", " + umfang + " Seiten";
        } else if (id == 3) {
            return bezeichnung + ", " + umfang + " Minuten";
        } else  if (id == 4) {
            return bezeichnung + ", " + umfang + " Stunden";
        } else {
            return bezeichnung + ", " + umfang + " Minuten";
        }
    }
}
