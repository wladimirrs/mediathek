package mediathek;

public interface Subject {
    void registriereObserver(Observer o);
    void entferneObserver(Observer o);
    void benachrichtigeObserver();
}
