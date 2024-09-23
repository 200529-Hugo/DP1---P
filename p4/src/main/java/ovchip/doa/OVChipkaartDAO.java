package ovchip.doa;

import ovchip.domain.OVChipkaart;
import ovchip.domain.Reiziger;

import java.util.List;

public interface OVChipkaartDAO {
    List<OVChipkaart> findByReiziger(Reiziger reiziger);
}
