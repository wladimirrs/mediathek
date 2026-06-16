package Klassen;

public class Artikel {

    private int id; // Attribute
    private Typ typ;
    private String titel;
    private boolean abachtzehn;
    private String genre;

                                // Konstruktoren
    public Artikel (int id, Typ typ, String titel, boolean abachtzehn, String genre) {
        this.id = id;
        this.typ = typ;
        this.titel = titel;
        this.abachtzehn = abachtzehn;
        this.genre = genre;
    }

    public Artikel (Typ typ, String titel, boolean abachtzehn, String genre) {
        this.typ = typ;
        this.titel = titel;
        this.abachtzehn = abachtzehn;
        this.genre = genre;
    }

    public Artikel (String titel) {
        this.titel = titel;
    }

    public Artikel () {}





    public int getId() {    // getter und setter
        return id;
    }

    public Typ getTyp() {
        return typ;
    }

    public String getTitel() {
        return titel;
    }

    public boolean isAbachtzehn() {
        return abachtzehn;
    }

    public String getGenre() {
        return genre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setAbachtzehn(boolean abachtzehn) {
        this.abachtzehn = abachtzehn;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    @Override
    public String toString() {
        return titel;
    }

}
