package Klassen;

public class Kunde {

    private int id; // Attribute
    private String nachname;
    private String vorname;
    private String mediatheknummer;
    private String strasse;
    private String hausnummer;
    private String plz;
    private String ort;
    private String geburtsdatum;

                                                                // Konstruktoren
    public Kunde(int id, String nachname, String vorname, String mediatheknummer, String strasse, String hausnummer, String plz, String ort, String geburtsdatum) {
        this.id = id;
        this.nachname = nachname;
        this.vorname = vorname;
        this.mediatheknummer = mediatheknummer;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.geburtsdatum = geburtsdatum;
    }

    public Kunde(String nachname, String vorname, String mediatheknummer, String strasse, String hausnummer, String plz, String ort, String geburtsdatum) {
        this.nachname = nachname;
        this.vorname = vorname;
        this.mediatheknummer = mediatheknummer;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.geburtsdatum = geburtsdatum;
    }

    public Kunde(String nachname, String vorname, String mediatheknummer) {
        this.nachname = nachname;
        this.vorname = vorname;
        this.mediatheknummer = mediatheknummer;
    }

    public Kunde() {}




    public int getId() {    // getter und setter
        return id;
    }

    public String getNachname() {
        return nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public String getMediatheknummer() {
        return mediatheknummer;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setMediatheknummer(String mediatheknummer) {
        this.mediatheknummer = mediatheknummer;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }


    @Override
    public String toString() {
        return mediatheknummer + ": " + nachname + " " + vorname;
    }

}
