package DAO;

import Klassen.Typ;
import mediathek.DB;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TypenDAO {

    public static ArrayList<Typ> getAll() {
        ArrayList<Typ> list = new ArrayList<>();
        String sql = "SELECT * FROM typen";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Typ t = new Typ(
                        rs.getInt("id"),
                        rs.getString("bezeichnung"),
                        rs.getInt("ausleihdauer")
                );
                list.add(t);
            }
        } catch (Exception e) {
            System.out.println("Fehlerhafte Suche: " + e.getMessage());
        }
        return list;
    }



    public static void delete (int id) {    // Löschen
        String sql = "DELETE FROM nutzer WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fehlerhaftes Löschen: " + e.getMessage());
        }
    }


    public static void insert (Typ t) { // Einfügen
        String sql = "INSERT INTO typen (bezeichnung, ausleihdauer) VALUES (?, ?)";
        try (Connection con = DB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getBezeichnung());
            ps.setInt(2, t.getAusleihdauer());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fehlerhaftes Einfügen: " + e.getMessage());
        }
    }


    public static void update (Typ t) {
        String sql = "UPDATE typen SET bezeichnung =?, ausleihdauer=? WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getBezeichnung());
            ps.setInt(2, t.getAusleihdauer());
            ps.setInt(3, t.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fehlerhaftes Ändern: " + e.getMessage());
        }
    }




}
