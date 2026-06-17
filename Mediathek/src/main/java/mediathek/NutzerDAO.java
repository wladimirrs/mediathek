package mediathek;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NutzerDAO {

    public static boolean register (String email, String passwort) {    // Registrierung einfacher insert
        String sql = "INSERT INTO nutzer (email, passwort) VALUES (?, ?)";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, passwort);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Fehler bei der Registrierung: " + e.getMessage());
            return false;
        }
    }


    public static boolean login (String email, String passwort) {       // Login Suche nach user und Rückgabe
        String sql = "SELECT * FROM nutzer WHERE email = ? AND passwort = ?";
        try (Connection con = DB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, passwort);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Fehler bei der Login: " + e.getMessage());
        }
        return false;
    }


}
