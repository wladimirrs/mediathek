package Klassen;

public class Artikel {

    private int id; // Attribute
    private Typ typ;
    private String titel;
    private boolean abachtzehn;
    private String genre;
    private int umfang;

                                // Konstruktoren
    public Artikel (int id, Typ typ, String titel, boolean abachtzehn, String genre, int  umfang) {
        this.id = id;
        this.typ = typ;
        this.titel = titel;
        this.abachtzehn = abachtzehn;
        this.genre = genre;
        this.umfang = umfang;
    }

    public Artikel (Typ typ, String titel, boolean abachtzehn, String genre, int  umfang) {
        this.typ = typ;
        this.titel = titel;
        this.abachtzehn = abachtzehn;
        this.genre = genre;
        this.umfang = umfang;
    }

    public Artikel (String titel, int  umfang) {
        this.titel = titel;
        this.umfang = umfang;
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

    public int getUmfang() { return umfang; }

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

    public void setUmfang(int umfang) { this.umfang = umfang; }


    @Override
    public String toString() {
        if (id == 1) {
            return titel + ", " + umfang + " Minuten";
        } else if (id == 2) {
            return titel + ", " + umfang + " Seiten";
        } else if (id == 3) {
            return titel + ", " + umfang + " Minuten";
        } else  if (id == 4) {
            return titel + ", " + umfang + " Stunden";
        } else {
            return titel + ", " + umfang + " Minuten";
        }
    }

}
