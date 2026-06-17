package DAO;

import Klassen.Kunde;
import mediathek.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KundenDAO {

    public static List<Kunde> getAll() {    // Suche
        List<Kunde> list = new ArrayList<>();
        String sql = "SELECT * FROM Kunden";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {    // Ergebnisse
         while (rs.next()) {    // während es noch Ergebnisse gibt
             Kunde k = new Kunde(   // erstelle Objekt
                     rs.getInt("id"),   // mit jeweiligem Inhalt
                     rs.getString("nachname"),
                     rs.getString("vorname"),
                     rs.getString("mediatheknummer"),
                     rs.getString("strasse"),
                     rs.getString("hausnummer"),
                     rs.getString("plz"),
                     rs.getString("ort"),
                     rs.getString("geburtsdatum")
             );
               list.add(k); // füge die Attribute der Liste hinzu
         }
        } catch (Exception e) {
            System.out.println("Fehlerhafte Suche: " + e.getMessage());
        }
        return list;
    }


    public static void delete(int id) { // Löschen
        String sql = "DELETE FROM kunden WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate(); // wo ausgelesene Ergebnisse, da auch ResultSet; ohne Ergebnisse nur executeUpdate()
        } catch (Exception e) {
            System.out.println("Fehlerhaftes Löschen: " + e.getMessage());
        }
    }



    public static void insert (Kunde k) {   // Einfügen (Datensatz, daher Kunde)
        String sql = "INSERT INTO kunden (nachname, vorname, mediatheknummer, strasse, hausnummer, plz, ort, geburtsdatum) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, k.getNachname());
            ps.setString(2, k.getVorname()); // bei get Spaltenname, bei set Parameter oder k.getXY
            ps.setString(3, k.getMediatheknummer());
            ps.setString(4, k.getStrasse());
            ps.setString(5, k.getHausnummer());
            ps.setString(6, k.getPlz());
            ps.setString(7, k.getOrt());
            ps.setString(8, k.getGeburtsdatum());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fehlerhaftes Einfügen: " + e.getMessage());
        }
    }



    public static void update (Kunde k) {   // Ändern (Datensatz)
        String sql = "UPDATE kunden SET nachname=?, vorname=?, mediatheknummer=?, strasse=?, hausnummer=?, plz=?, ort=?, geburtsdatum=? WHERE id=?";
        try (Connection con = DB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, k.getNachname());
            ps.setString(2, k.getVorname());
            ps.setString(3, k.getMediatheknummer());
            ps.setString(4, k.getStrasse());
            ps.setString(5, k.getHausnummer());
            ps.setString(6, k.getPlz());
            ps.setString(7, k.getOrt());
            ps.setString(8, k.getGeburtsdatum());
            ps.setInt(9, k.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fehlerhaftes Ändern: " +e.getMessage());
        }
    }




}
