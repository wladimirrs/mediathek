package DAO;

import Klassen.Artikel;
import Klassen.Typ;
import mediathek.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArtikelDAO {

    public static ArrayList<Artikel> getAll() {  // Suche
        ArrayList<Artikel> list = new ArrayList<>();
        String sql = "SELECT a.*, " +
                "t.bezeichnung, t.umfang" +
                " FROM artikel a LEFT JOIN typen t ON a.typ = t.id ";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Typ typ = new Typ(
                        rs.getString("bezeichnung"),
                        rs.getInt("umfang")
                );
                Artikel a = new Artikel(
                        rs.getInt("id"),
                        typ,
                        rs.getString("titel"),
                        rs.getBoolean("abachtzehn"),
                        rs.getString("genre")
                );
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("Fehlerhafte Suche: " + e.getMessage());
        }
        return list;
    }


    public static void delete (int id) {    // Löschen
        String sql = "DELETE FROM artikel WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fehlerhaftes Löschen: " + e.getMessage());
        }
    }


    public static void insert (Artikel a) { // Einfügen
        String sql = "INSERT INTO artikel VALUES (?, ?, ?, ?)";
        try (Connection con = DB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getTyp().getId());
            ps.setString(2, a.getTitel());
            ps.setBoolean(3, a.isAbachtzehn());
            ps.setString(4, a.getGenre());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fehlerhaftes Einfügen: " + e.getMessage());
        }
    }


    public static void update  (Artikel a) {
        String sql = "UPDATE artikel SET typ=?, titel=?, abachtzehn=?, genre =? WHERE id=?";
        try (Connection con = DB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getTyp().getId());
            ps.setString(2, a.getTitel());
            ps.setBoolean(3, a.isAbachtzehn());
            ps.setString(4, a.getGenre());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fehlerhaftes Ändern: " + e.getMessage());
        }
    }




}
