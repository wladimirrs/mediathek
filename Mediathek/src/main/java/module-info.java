module package1.mediathek {
    requires javafx.controls;
    requires javafx.fxml;


    opens package1.mediathek to javafx.fxml;
    exports package1.mediathek;
}