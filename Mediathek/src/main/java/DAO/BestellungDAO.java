package DAO;

import Klassen.Artikel;
import Klassen.Bestellung;
import Klassen.Kunde;
import mediathek.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BestellungDAO {

    public static ArrayList<Bestellung> getAll() {   // Suche
        ArrayList<Bestellung> list = new ArrayList<>();
        String sql = "SELECT b.*, " +
        "k.mediatheknummer, k.nachname, k.vorname, " +
                "a.titel" +
                " FROM bestellungen b LEFT JOIN kunden k ON b.kunden_id = k.id " +
                "LEFT JOIN artikel a ON b.artikel_id = a.id ";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Kunde kunde = new Kunde(
                        rs.getString("mediatheknummer"),
                        rs.getString("nachname"),
                        rs.getString("vorname")
                );
                Artikel artikel = new Artikel(
                        rs.getString("titel"),
                        rs.getInt("umfang")
                );
                Bestellung b = new Bestellung(
                        rs.getInt("id"),
                        kunde,
                        artikel,
                        rs.getString("von"),
                        rs.getString("bis")
                );
                list.add(b);
            }
        } catch (Exception e) {
            System.out.println("Fehlerhafte Suche: " + e.getMessage());
        }
        return list;
    }

    public static void delete (int id) {    // Löschen
        String sql = "DELETE FROM bestellungen WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fehlerhaftes Löschen: " + e.getMessage());
        }
    }


    public static void insert (Bestellung b) {  // Einfügen
        String sql = "INSERT INTO bestellungen VALUES (?, ?, ?, ?)";
        try (Connection con = DB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, b.getKunde().getId());
            ps.setInt(2, b.getArtikel().getId());
            ps.setString(3, b.getVon());
            ps.setString(4, b.getBis());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fehlerhaftes Einfügen: " + e.getMessage());
        }
    }


    public static void update  (Bestellung b) { // Ändern
        String sql = "UPDATE bestellungen SET kunden_id=?, artikel_id=?, von=?, bis=? WHERE id=?";
        try ( Connection con = DB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, b.getKunde().getId());
            ps.setInt(2, b.getArtikel().getId());
            ps.setString(3, b.getVon());
            ps.setString(4, b.getBis());
            ps.setInt(5, b.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fehlerhaftes Ändern: " + e.getMessage());
        }
    }



}
