package mediathek;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.net.URL;

public class ViewLoader {

    private Pane view;



    public Pane loadView(String filename) {
        try {
            URL fileurl = DashboardController.class.getResource("/mediathek/" + filename + ".fxml");
            if (fileurl == null) {
                throw new FileNotFoundException("File not found: " + filename);
            }
            FXMLLoader loader = new FXMLLoader(fileurl);
            view = loader.load();
        } catch (Exception e) {
            System.out.println("Keine View " + filename + ". Fehler: " + e.getMessage());
            e.printStackTrace();
        }
        return view;
    }
}
