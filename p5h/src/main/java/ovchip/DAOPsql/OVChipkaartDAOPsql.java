package ovchip.DAOPsql;

import ovchip.dao.OVChipkaartDAO;
import ovchip.domain.OVChipkaart;
import ovchip.domain.Reiziger;

import javax.persistence.EntityManager;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    // Attributes
    private final EntityManager em;

    // Constructor
    public OVChipkaartDAOPsql(EntityManager em) {
        this.em = em;
    }

    // Save OVChipkaart
    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        try {
            em.getTransaction().begin();
            em.persist(ovChipkaart);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update OVChipkaart
    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        try {
            em.getTransaction().begin();
            em.remove(ovChipkaart);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Find OVChipkaart by reiziger
    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        return em.createQuery("SELECT o FROM OVChipkaart o WHERE o.reiziger = :reiziger", OVChipkaart.class)
                .setParameter("reiziger", reiziger)
                .getResultList();
    }
}
