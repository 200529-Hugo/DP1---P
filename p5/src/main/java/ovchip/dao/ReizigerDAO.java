package ovchip.dao;

import ovchip.domain.Reiziger;

import java.util.List;

public interface ReizigerDAO {
    // CRUD operations
    boolean save(Reiziger reiziger);
    boolean update(Reiziger reiziger);
    boolean delete(Reiziger reiziger);
    Reiziger findById(int id);
    List<Reiziger> findByGbdatum(String datum);
    List<Reiziger> findAll();
}

