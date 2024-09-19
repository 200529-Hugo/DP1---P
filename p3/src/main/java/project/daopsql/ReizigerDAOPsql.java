package project.daopsql;

import project.dao.ReizigerDAO;
import project.domain.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    // Fields
    private Connection conn;

    // Constructor
    public ReizigerDAOPsql(Connection conn) {
        this.conn = conn;
    }

    // Methods
    // Save a reiziger
    @Override
    public boolean save(Reiziger reiziger) {
        String query = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, reiziger.getId());
            pst.setString(2, reiziger.getVoorletters());
            pst.setString(3, reiziger.getTussenvoegsel());
            pst.setString(4, reiziger.getAchternaam());
            pst.setDate(5, new Date(reiziger.getGeboortedatum().getTime()));
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update a reiziger
    @Override
    public boolean update(Reiziger reiziger) {
        String query = "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? WHERE reiziger_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, reiziger.getVoorletters());
            pst.setString(2, reiziger.getTussenvoegsel());
            pst.setString(3, reiziger.getAchternaam());
            pst.setDate(4, new Date(reiziger.getGeboortedatum().getTime()));
            pst.setInt(5, reiziger.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a reiziger
    @Override
    public boolean delete(Reiziger reiziger) {
        String query = "DELETE FROM reiziger WHERE reiziger_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, reiziger.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Find a reiziger by id
    @Override
    public Reiziger findById(int id) {
        String query = "SELECT reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum FROM reiziger WHERE reiziger_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Reiziger(
                        rs.getInt("reiziger_id"),
                        rs.getString("voorletters"),
                        rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"),
                        rs.getDate("geboortedatum")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Find reizigers by geboortedatum
    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        String query = "SELECT reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum FROM reiziger WHERE geboortedatum = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setDate(1, Date.valueOf(datum));
            ResultSet rs = pst.executeQuery();
            List<Reiziger> reizigers = new ArrayList<>();
            while (rs.next()) {
                reizigers.add(new Reiziger(
                        rs.getInt("reiziger_id"),
                        rs.getString("voorletters"),
                        rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"),
                        rs.getDate("geboortedatum")
                ));
            }
            return reizigers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Find all reizigers
    @Override
    public List<Reiziger> findAll() {
        String query = "SELECT reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum FROM reiziger";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            List<Reiziger> reizigers = new ArrayList<>();
            while (rs.next()) {
                reizigers.add(new Reiziger(
                        rs.getInt("reiziger_id"),
                        rs.getString("voorletters"),
                        rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"),
                        rs.getDate("geboortedatum")
                ));
            }
            return reizigers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

