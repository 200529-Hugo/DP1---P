package opdracht.daop;

import opdracht.dao.ProductDAO;
import opdracht.domain.OVchipkaart;
import opdracht.domain.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductDAOH implements ProductDAO {
    private final EntityManager em;

    public ProductDAOH(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean save(Product product) {
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addOVChipkaart(Product product, OVchipkaart ovChipkaart, String status) {
        try {
            em.getTransaction().begin();
            product.getOvChipkaarten().add(ovChipkaart);
            em.merge(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try {
            em.getTransaction().begin();
            em.remove(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> findByOVChipkaart(OVchipkaart ovChipkaart) {
        try {
            return em.createQuery("SELECT p FROM Product p WHERE :ovChipkaart MEMBER OF p.ovChipkaarten", Product.class)
                    .setParameter("ovChipkaart", ovChipkaart)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product findById(int id) {
        return em.find(Product.class, id);
    }
}
