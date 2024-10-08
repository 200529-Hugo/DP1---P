package opdracht.dao;

import opdracht.domain.OVchipkaart;
import opdracht.domain.Reiziger;

import java.util.List;

public interface OVchipkaartDAO {
    // CRUD
    boolean save(OVchipkaart ovchipkaart);
    boolean update(OVchipkaart ovchipkaart);
    boolean delete(OVchipkaart ovchipkaart);
    OVchipkaart findById(int id);
    List<OVchipkaart> findAll();
    List<OVchipkaart> findByReiziger(Reiziger reiziger);
}
