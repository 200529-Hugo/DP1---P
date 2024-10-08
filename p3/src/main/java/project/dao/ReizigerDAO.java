package project.dao;

import project.domain.Reiziger;

import java.util.List;

public interface ReizigerDAO {
    // CRUD methods
    boolean save(Reiziger reiziger);
    boolean update(Reiziger reiziger);
    boolean delete(Reiziger reiziger);
    Reiziger findById(int id);
    List<Reiziger> findByGbdatum(String datum);
    List<Reiziger> findAll();
}

