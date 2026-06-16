package Klassen;

public class Bestellung {


    private int id; // Attribute
    private Kunde kunde;
    private Artikel artikel;
    private String von;
    private String bis;


    public Bestellung (int id, Kunde kunde, Artikel artikel, String von, String bis) {
        this.id = id;
        this.kunde = kunde;
        this.artikel = artikel;
        this.von = von;
        this.bis = bis;
    }

    public Bestellung (Kunde kunde, Artikel artikel, String von, String bis) {
        this.kunde = kunde;
        this.artikel = artikel;
        this.von = von;
        this.bis = bis;
    }

    public Bestellung () {}





    public int getId() {    // getter und setter
        return id;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public String getVon() {
        return von;
    }

    public String getBis() {
        return bis;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public void setVon(String von) {
        this.von = von;
    }

    public void setBis(String bis) {
        this.bis = bis;
    }
}
