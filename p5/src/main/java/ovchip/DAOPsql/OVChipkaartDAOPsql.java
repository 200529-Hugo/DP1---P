package ovchip.DAOPsql;

import ovchip.dao.OVChipkaartDAO;
import ovchip.domain.OVChipkaart;
import ovchip.domain.Product;
import ovchip.domain.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    // Attributes
    private Connection conn;

    // Constructor
    public OVChipkaartDAOPsql(Connection conn) {
        this.conn = conn;
    }

    // Save an OVChipkaart to the database
    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        // Implement logic to save OVChipkaart to the database
        String query = "INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, ovChipkaart.getId());
            pst.setDate(2, ovChipkaart.getGeldigTot());
            pst.setInt(3, ovChipkaart.getKlasse());
            pst.setDouble(4, ovChipkaart.getSaldo());
            pst.setInt(5, ovChipkaart.getReiziger().getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Add a Product to an OVChipkaart in the database
    @Override
    public boolean addProduct(OVChipkaart ovChipkaart, Product product, String status) {
        String query = "INSERT INTO ov_chipkaart_product (kaart_nummer, product_nummer, status, last_update) VALUES (?, ?, ?, CURRENT_DATE)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, ovChipkaart.getId());
            pst.setInt(2, product.getProductNummer());
            pst.setString(3, status);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Find an OVChipkaart by a Reiziger
    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        // Implement logic to find OVChipkaart by Reiziger
        String query = "SELECT kaart_nummer, geldig_tot, klasse, saldo, reiziger_id FROM ov_chipkaart WHERE reiziger_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, reiziger.getId());
            ResultSet rs = pst.executeQuery();
            List<OVChipkaart> ovChipkaarts = new ArrayList<>();
            while (rs.next()) {
                OVChipkaart ovChipkaart = new OVChipkaart(rs.getInt("kaart_nummer"), rs.getDate("geldig_tot"), rs.getInt("klasse"), rs.getDouble("saldo"), reiziger);
                ovChipkaarts.add(ovChipkaart);
            }
            return ovChipkaarts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // Delete an OVChipkaart from the database
    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        // Implement logic to delete OVChipkaart from the database
        deleteFromManyToMany(ovChipkaart);

        String query = "DELETE FROM ov_chipkaart WHERE kaart_nummer = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, ovChipkaart.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cascade delete from many-to-many table
    public void deleteFromManyToMany(OVChipkaart ovChipkaart) {
        String query = "DELETE FROM ov_chipkaart_product WHERE kaart_nummer = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, ovChipkaart.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

