package opdracht.daopSql;

import opdracht.dao.OVChipkaartDAO;
import opdracht.domain.OVChipkaart;
import opdracht.domain.Reiziger;

import javax.persistence.EntityManager;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    // Attributes
    private final EntityManager em;

    // Constructor
    public OVChipkaartDAOPsql(EntityManager em) {
        this.em = em;
    }

    // Returns a list of OVChipkaart objects that belong to a specific Reiziger
    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        return em.createQuery("SELECT o FROM OVChipkaart o WHERE o.reiziger = :reiziger", OVChipkaart.class)
                .setParameter("reiziger", reiziger)
                .getResultList();
    }
}
