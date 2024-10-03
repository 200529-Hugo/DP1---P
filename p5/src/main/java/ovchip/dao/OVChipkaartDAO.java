package ovchip.dao;

import ovchip.domain.OVChipkaart;
import ovchip.domain.Product;
import ovchip.domain.Reiziger;

import java.util.List;

public interface OVChipkaartDAO {
    // CRUD operations
    boolean save(OVChipkaart ovChipkaart);
    boolean addProduct(OVChipkaart ovChipkaart, Product product, String status);
    List<OVChipkaart> findByReiziger(Reiziger reiziger);

    boolean delete(OVChipkaart ovChipkaart);
}
