package opdracht.dao;

import opdracht.domain.OVchipkaart;
import opdracht.domain.Product;

import java.util.List;

public interface ProductDAO {
    boolean save(Product product);
    boolean update(Product product);
    boolean addOVChipkaart(Product product, OVchipkaart ovChipkaart, String status);
    boolean delete(Product product);
    Product findById(int id);
    List<Product> findByOVChipkaart(OVchipkaart ovChipkaart);
    List<Product> findAll();
}
