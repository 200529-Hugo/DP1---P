package opdracht.daop;

import opdracht.dao.OVchipkaartDAO;
import opdracht.domain.OVchipkaart;
import opdracht.domain.Reiziger;

import javax.persistence.EntityManager;
import java.util.List;

public class OVchipkaartDAOH implements OVchipkaartDAO {
    private final EntityManager em;

    public OVchipkaartDAOH(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean save(OVchipkaart ovchipkaart) {
        try {
            em.getTransaction().begin();
            em.persist(ovchipkaart);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OVchipkaart ovchipkaart) {
        try {
            em.getTransaction().begin();
            em.merge(ovchipkaart);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(OVchipkaart ovchipkaart) {
        try {
            em.getTransaction().begin();
            em.remove(ovchipkaart);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public OVchipkaart findById(int id) {
        return em.find(OVchipkaart.class, id);
    }

    @Override
    public List<OVchipkaart> findAll() {
        return em.createQuery("SELECT o FROM OVchipkaart o", OVchipkaart.class).getResultList();
    }

    @Override
    public List<OVchipkaart> findByReiziger(Reiziger reiziger) {
        return em.createQuery("SELECT o FROM OVchipkaart o WHERE o.reiziger = :reiziger", OVchipkaart.class)
                .setParameter("reiziger", reiziger)
                .getResultList();
    }
}
