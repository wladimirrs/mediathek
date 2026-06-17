package mediathek;

public interface Observer { // von ModelService aufgerufen, wenn daten sich ändern
    void updateView();
}
