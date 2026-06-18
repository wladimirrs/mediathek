module mediathek {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens mediathek to javafx.fxml;
    exports mediathek;
    exports Klassen;
    opens Klassen to javafx.fxml, javafx.graphics;
    exports DAO;
    opens DAO to javafx.fxml, javafx.graphics;
    exports Controller;
    opens Controller to javafx.fxml, javafx.graphics;
    exports Service;
    opens Service to javafx.fxml, javafx.graphics;
}