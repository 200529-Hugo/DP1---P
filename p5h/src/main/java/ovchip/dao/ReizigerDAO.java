package ovchip.dao;

import ovchip.domain.Reiziger;

import java.util.Date;
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

