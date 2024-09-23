package ovchip.DAOPsql;

import ovchip.doa.OVChipkaartDAO;
import ovchip.doa.ReizigerDAO;
import ovchip.domain.OVChipkaart;
import ovchip.domain.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection conn;
    private ReizigerDAO rdao;


    public OVChipkaartDAOPsql(Connection conn, ReizigerDAO rdao) {
        this.conn = conn;
        this.rdao = rdao;
    }

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
        return null;
    }
}

