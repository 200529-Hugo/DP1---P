package ovchip.DAOPsql;

import ovchip.dao.ProductDAO;
import ovchip.domain.OVChipkaart;
import ovchip.domain.Product;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProductDAOPsql implements ProductDAO {
    // Attributes
    private final EntityManager em;

    // Constructor
    public ProductDAOPsql(EntityManager em) {
        this.em = em;
    }

    // Save product
    @Override
    public boolean save(Product product) {
        Transaction tx = null;
        try {
            tx = (Transaction) em.getTransaction();
            tx.begin();
            em.persist(product);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Update product
    @Override
    public boolean update(Product product) {
        Transaction tx = null;
        try {
            tx = (Transaction) em.getTransaction();
            tx.begin();
            em.merge(product);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Add a relationship between Product and OVChipkaart
    @Override
    public boolean addOVChipkaart(Product product, OVChipkaart ovChipkaart, String status) {
        Transaction tx = null;
        try {
            tx = (Transaction) em.getTransaction();
            tx.begin();
            product.getOvChipkaarten().add(ovChipkaart);
            em.merge(product);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Delete product
    @Override
    public boolean delete(Product product) {
        Transaction tx = null;
        try {
            tx = (Transaction) em.getTransaction();
            tx.begin();
            em.remove(em.contains(product) ? product : em.merge(product));
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Find products by OVChipkaart
    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) {
        Query query = em.createQuery("SELECT p FROM Product p JOIN p.ovChipkaarten o WHERE o.id = :kaartNummer", Product.class);
        query.setParameter("kaartNummer", ovChipkaart.getId());
        return query.getResultList();
    }

    // Find all products
    @Override
    public List<Product> findAll() {
        Query query = em.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
    }
}
