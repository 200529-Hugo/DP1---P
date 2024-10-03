package ovchip.dao;

import ovchip.domain.OVChipkaart;
import ovchip.domain.Product;

import java.util.List;

public interface ProductDAO {
    // CRUD operations
    boolean save(Product product);
    boolean update(Product product);
    boolean addOVChipkaart(Product product, OVChipkaart ovChipkaart, String status);
    boolean delete(Product product);
    List<Product> findByOVChipkaart(OVChipkaart ovChipkaart);
    List<Product> findAll();
}
