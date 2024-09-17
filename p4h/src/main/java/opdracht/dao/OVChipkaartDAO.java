package opdracht.dao;

import opdracht.domain.OVChipkaart;
import opdracht.domain.Reiziger;

import java.util.List;

public interface OVChipkaartDAO {
    // Read
    List<OVChipkaart> findByReiziger(Reiziger reiziger);
}
