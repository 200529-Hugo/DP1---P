package opdracht.daop;

import opdracht.dao.ReizigerDAO;
import opdracht.domain.Reiziger;

//import jakarta.persistence.*;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

public class ReizigerDAOH implements ReizigerDAO {
    private final EntityManager em;

    public ReizigerDAOH(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            em.getTransaction().begin();
            em.persist(reiziger);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            em.getTransaction().begin();
            em.merge(reiziger);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            em.getTransaction().begin();
            em.remove(reiziger);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        return em.find(Reiziger.class, id);
    }

    @Override
    public List<Reiziger> findAll() {
        try {
            return em.createQuery("SELECT r FROM Reiziger r", Reiziger.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(Date datum) {
        try {
            return em.createQuery("SELECT r FROM Reiziger r WHERE r.geboortedatum = :datum", Reiziger.class)
                    .setParameter("datum", datum)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
