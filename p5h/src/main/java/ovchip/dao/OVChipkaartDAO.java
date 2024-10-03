package ovchip.dao;

import ovchip.domain.OVChipkaart;
import ovchip.domain.Reiziger;

import java.util.List;

public interface OVChipkaartDAO {
    // Read
    boolean save(OVChipkaart ovChipkaart);
    boolean delete(OVChipkaart ovChipkaart);
    List<OVChipkaart> findByReiziger(Reiziger reiziger);
}
