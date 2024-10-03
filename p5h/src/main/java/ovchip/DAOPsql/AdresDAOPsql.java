package ovchip.DAOPsql;

import ovchip.dao.AdresDAO;
import ovchip.domain.Adres;
import ovchip.domain.Reiziger;

import javax.persistence.EntityManager;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {

    // Attributes
    private final EntityManager em;

    // Constructor
    public AdresDAOPsql(EntityManager em) {
        this.em = em;
    }

    // Saves an address
    @Override
    public boolean save(Adres adres) {
        try {
            em.getTransaction().begin();
            em.persist(adres);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    // Updates the address
    @Override
    public boolean update(Adres adres) {
        try {
            em.getTransaction().begin();
            em.merge(adres);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    // Deletes an address
    @Override
    public boolean delete(Adres adres) {
        try {
            em.getTransaction().begin();
            em.remove(adres);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    // Finds the address of a specific reiziger
    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        return em.createQuery("SELECT a FROM Adres a WHERE a.reiziger = :reiziger", Adres.class)
                .setParameter("reiziger", reiziger)
                .getSingleResult();
    }

    // Finds all addresses
    @Override
    public List<Adres> findAll() {
        return em.createQuery("SELECT a FROM Adres a", Adres.class).getResultList();
    }
}
