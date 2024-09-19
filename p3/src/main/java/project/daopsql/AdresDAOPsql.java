package project.daopsql;

import project.dao.AdresDAO;
import project.dao.ReizigerDAO;
import project.domain.Adres;
import project.domain.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    // Fields
    private final Connection conn;
    private final ReizigerDAO rdao;

    // Constructor
    public AdresDAOPsql(Connection conn, ReizigerDAO rdao) {
        this.conn = conn;
        this.rdao = rdao;
    }

    // Methods
    // Save an adres
    @Override
    public boolean save(Adres adres) {
        String query = "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, adres.getId());
            pst.setString(2, adres.getPostcode());
            pst.setString(3, adres.getHuisnummer());
            pst.setString(4, adres.getStraat());
            pst.setString(5, adres.getWoonplaats());
            pst.setInt(6, adres.getReiziger().getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an adres
    @Override
    public boolean update(Adres adres) {
        String query = "UPDATE adres SET postcode = ?, huisnummer = ?, straat = ?, woonplaats = ? WHERE adres_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, adres.getPostcode());
            pst.setString(2, adres.getHuisnummer());
            pst.setString(3, adres.getStraat());
            pst.setString(4, adres.getWoonplaats());
            pst.setInt(5, adres.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an adres
    @Override
    public boolean delete(Adres adres) {
        String query = "DELETE FROM adres WHERE adres_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, adres.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Find an adres by reiziger
    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        String query = "SELECT adres_id, postcode, huisnummer, straat, woonplaats FROM adres WHERE reiziger_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, reiziger.getId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Adres(
                        rs.getInt("adres_id"),
                        rs.getString("postcode"),
                        rs.getString("huisnummer"),
                        rs.getString("straat"),
                        rs.getString("woonplaats"),
                        reiziger
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Find all adressen
    @Override
    public List<Adres> findAll() {
        String query = "SELECT adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id FROM adres";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            List<Adres> adressen = new ArrayList<>();
            while (rs.next()) {
                Reiziger reiziger = rdao.findById(rs.getInt("reiziger_id"));
                adressen.add(new Adres(
                        rs.getInt("adres_id"),
                        rs.getString("postcode"),
                        rs.getString("huisnummer"),
                        rs.getString("straat"),
                        rs.getString("woonplaats"),
                        reiziger
                ));
            }
            return adressen;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

