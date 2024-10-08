package opdracht.dao;

import opdracht.domain.Reiziger;

import java.sql.Date;
import java.util.List;

public interface ReizigerDAO {
    // CRUD
    boolean save(Reiziger reiziger);
    boolean update(Reiziger reiziger);
    boolean delete(Reiziger reiziger);
    Reiziger findById(int id);
    List<Reiziger> findAll();
    List<Reiziger> findByGbdatum(Date datum);
}
