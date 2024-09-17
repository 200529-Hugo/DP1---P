package opdracht.daopSql;

import opdracht.dao.ReizigerDAO;
import opdracht.domain.Reiziger;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    // Attributes
    private final EntityManager em;

    // Constructor
    public ReizigerDAOPsql(EntityManager em) {
        this.em = em;
    }

    // Save reiziger
    @Override
    public boolean save(Reiziger reiziger) {
        Transaction tx = null;
        try {
            tx = (Transaction) em.getTransaction();
            tx.begin();
            em.persist(reiziger);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Update reiziger
    @Override
    public boolean update(Reiziger reiziger) {
        Transaction tx = null;
        try {
            tx = (Transaction) em.getTransaction();
            tx.begin();
            em.merge(reiziger);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Delete reiziger
    @Override
    public boolean delete(Reiziger reiziger) {
        Transaction tx = null;
        try {
            tx = (Transaction) em.getTransaction();
            tx.begin();
            em.remove(em.contains(reiziger) ? reiziger : em.merge(reiziger));
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Find reiziger by id
    @Override
    public Reiziger findById(int id) {
        return em.find(Reiziger.class, id);
    }

    // Find all reizigers
    @Override
    public List<Reiziger> findAll() {
        Query query = em.createQuery("SELECT r FROM Reiziger r");
        return query.getResultList();
    }

    // Find reiziger by geboortedatum
    @Override
    public List<Reiziger> findByGbdatum(Date datum) {
        Query query = em.createQuery("SELECT r FROM Reiziger r WHERE r.geboortedatum = :datum");
        query.setParameter("datum", datum);
        return query.getResultList();
    }
}

