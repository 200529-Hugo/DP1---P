package opdracht.daop;

import opdracht.dao.AdresDAO;
import opdracht.domain.Adres;
import opdracht.domain.Reiziger;

import javax.persistence.EntityManager;
import java.util.List;

public class AdresDAOH implements AdresDAO {
    private final EntityManager em;

    public AdresDAOH(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            em.getTransaction().begin();
            em.persist(adres);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try {
            em.getTransaction().begin();
            em.merge(adres);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        try {
            em.getTransaction().begin();
            em.remove(adres);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Adres findById(int id) {
        return em.find(Adres.class, id);
    }

    @Override
    public List<Adres> findAll() {
        return em.createQuery("SELECT a FROM Adres a", Adres.class).getResultList();
    }

    @Override
    public List<Adres> findByReiziger(Reiziger reiziger) {
        return em.createQuery("SELECT a FROM Adres a WHERE a.reiziger = :reiziger", Adres.class)
                .setParameter("reiziger", reiziger)
                .getResultList();
    }
}
