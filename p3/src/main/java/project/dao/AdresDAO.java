package project.dao;

import project.domain.Adres;
import project.domain.Reiziger;

import java.util.List;

public interface AdresDAO {
    // CRUD methods
    boolean save(Adres adres);
    boolean update(Adres adres);
    boolean delete(Adres adres);
    Adres findByReiziger(Reiziger reiziger);
    List<Adres> findAll();
}
