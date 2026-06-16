package mediathek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
                                // Verbindung privat
    private static DB instance;
    private Connection connection;

    private static final String url = "jdbc:mysql://localhost:3306/mediathek";
    private static final String user = "root";
    private static final String password = "";

                                // Konstruktor
    private DB() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }
                                // wenn es keine Verbindung gibt, erzeuge eine
    public static DB getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DB();
        }
        return instance;
    }

                                // Methode
    public Connection getConnection() {
        return connection;
    }

}
