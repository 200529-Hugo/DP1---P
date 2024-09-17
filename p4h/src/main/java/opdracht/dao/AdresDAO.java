package opdracht.dao;

import opdracht.domain.Adres;
import opdracht.domain.Reiziger;

import java.util.List;

public interface AdresDAO {
    // CRUD
    boolean save(Adres adres);
    boolean update(Adres adres);
    boolean delete(Adres adres);
    Adres findByReiziger(Reiziger reiziger);
    List<Adres> findAll();
}
