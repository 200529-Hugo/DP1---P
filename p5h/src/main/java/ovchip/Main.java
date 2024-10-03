package ovchip;

import ovchip.DAOPsql.AdresDAOPsql;
import ovchip.DAOPsql.OVChipkaartDAOPsql;
import ovchip.DAOPsql.ProductDAOPsql;
import ovchip.DAOPsql.ReizigerDAOPsql;
import ovchip.dao.AdresDAO;
import ovchip.dao.OVChipkaartDAO;
import ovchip.dao.ProductDAO;
import ovchip.dao.ReizigerDAO;
import ovchip.domain.Adres;
import ovchip.domain.OVChipkaart;
import ovchip.domain.Product;
import ovchip.domain.Reiziger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;

public class Main {
    // Create an EntityManagerFactory when the application is started
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ovchipkaartPU");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        // Create DAO's
        ReizigerDAO reizigerDAO = new ReizigerDAOPsql(em);
        AdresDAO adresDAO = new AdresDAOPsql(em);
        OVChipkaartDAO ovChipkaartDAO = new OVChipkaartDAOPsql(em);
        ProductDAO productDAO = new ProductDAOPsql(em);


        // Test DAO's
        testReizigerDAO(reizigerDAO);
        testAdresDAO(adresDAO, reizigerDAO);
        testOVChipkaartDAO(ovChipkaartDAO, reizigerDAO);
        testProductDAO(productDAO, ovChipkaartDAO, reizigerDAO);

        em.close();
        emf.close();
    }

    // Test the ReizigerDAO
    public static void testReizigerDAO(ReizigerDAO rdao) {
        // Get all reizigers from the database
        System.out.println("---- Test ReizigerDAO ----");
        System.out.println("Alle reizigers: ");
        for (Reiziger r : rdao.findAll()) {
            System.out.println(r);
        }
        System.out.println();

        // Get reiziger with id 2
        System.out.println("Reiziger met id 2: ");
        System.out.println(rdao.findById(2));
        System.out.println();

        // Get reizigers with birthdate 2002-10-22
        System.out.println("Reizigers met geboortedatum 2002-10-22: ");
        for (Reiziger r : rdao.findByGbdatum(java.sql.Date.valueOf("2002-10-22"))) {
            System.out.println(r);
        }
        System.out.println();

        // Create a new reiziger and persist it in the database
        Reiziger reiziger = new Reiziger(6, "H", "de", "Heus", Date.valueOf("2004-02-23"));
        System.out.print("Reiziger toevoegen: ");
        if (rdao.save(reiziger)) {
            System.out.println("Gelukt!");
            System.out.println("Reiziger: " + reiziger);
        } else {
            System.out.println("Mislukt!");
        }
        System.out.println();

        // Update the new reiziger in the database
        reiziger.setAchternaam("Groot");
        System.out.print("Reiziger updaten: ");
        if (rdao.update(reiziger)) {
            System.out.println("Gelukt!");
            System.out.println("Reiziger: " + reiziger);
        } else {
            System.out.println("Mislukt!");
        }
        System.out.println();

        // Delete the new reiziger from the database
        System.out.print("Reiziger verwijderen: ");
        if (rdao.delete(reiziger)) {
            System.out.println("Gelukt!");
        } else {
            System.out.println("Mislukt!");
        }
        System.out.println();
    }

    // Test the AdresDAO
    public static void testAdresDAO(AdresDAO adao, ReizigerDAO rdao) {
        // Get all addresses from the database
        System.out.println("---- Test AdresDAO ----");
        System.out.println("Alle adressen: ");
        for (Adres a : adao.findAll()) {
            System.out.println(a);
        }
        System.out.println();

        // Get address of reiziger 2
        System.out.println("Adres van reiziger 2: ");
        System.out.println(adao.findByReiziger(rdao.findById(2)));
        System.out.println();

        // Create a new reiziger and address and persist it in the database
        Reiziger reiziger = new Reiziger(6, "H", "de", "Heus", Date.valueOf("2004-02-23"));
        rdao.save(reiziger);
        Adres reizigerAdres = new Adres(6, "1234AB", "1", "Straatweg", "Utrecht", reiziger);
        System.out.print("Adres toevoegen: ");
        if (adao.save(reizigerAdres)) {
            System.out.println("Gelukt!");
            System.out.println("Adres: " + reizigerAdres);
        } else {
            System.out.println("Mislukt!");
        }
        System.out.println();

        // Update the new address in the database
        reizigerAdres.setHuisnummer("2");
        System.out.print("Adres updaten: ");
        if (adao.update(reizigerAdres)) {
            System.out.println("Gelukt!");
            System.out.println("Adres: " + reizigerAdres);
        } else {
            System.out.println("Mislukt!");
        }
        System.out.println();

        // Delete the new address from the database
        System.out.print("Adres verwijderen: ");
        if (adao.delete(reizigerAdres)) {
            System.out.println("Gelukt!");
        } else {
            System.out.println("Mislukt!");
        }
        rdao.delete(reiziger);
        System.out.println();
    }

    // Test the OVChipkaartDAO
    public static void testOVChipkaartDAO(OVChipkaartDAO ovdao, ReizigerDAO rdao) {
        System.out.println("---- Test OVChipkaartDAO ----");

        // Get ov-chipkaarten from reiziger 2
        System.out.println("Zoek OV-chipkaarten van reiziger 2: ");
        for (OVChipkaart ov : ovdao.findByReiziger(rdao.findById(2))) {
            System.out.println(ov);
        }
    }

    // Test the ProductDAO
    public static void testProductDAO(ProductDAO productDAO, OVChipkaartDAO ovChipkaartDAO, ReizigerDAO reizigerDAO) {
        System.out.println("---- Test ProductDAO ----");

        // Create 2 new products
        Product product = new Product(6, "Product 1", "Beschrijving", 10.00);
        System.out.print("Saving product: ");
        if (productDAO.save(product)) {
            System.out.println("Product 1 saved!");
            System.out.println(product);
        } else {
            System.out.println("Failed!");
        }
        System.out.println();

        Product product2 = new Product(7, "Product 2", "Beschrijving", 20.00);
        System.out.print("Saving product: ");
        if (productDAO.save(product2)) {
            System.out.println("Product 2 saved!");
            System.out.println(product2);
        } else {
            System.out.println("Failed!");
        }


        // Update product
        product.setNaam("Product updated");
        System.out.print("Updating product: ");
        if (productDAO.update(product)) {
            System.out.println("Success!");
            System.out.println(product);
        } else {
            System.out.println("Failed!");
        }
        System.out.println();

        // Create a new Reiziger and OVChipkaart
        Reiziger reiziger = new Reiziger(6, "H", "de", "Heus", Date.valueOf("2004-02-23"));
        reizigerDAO.save(reiziger);
        OVChipkaart ovChipkaart = new OVChipkaart(6, Date.valueOf("2020-01-01"), 1, 10.00, reiziger);
        System.out.print("Saving OVChipkaart: ");
        if (ovChipkaartDAO.save(ovChipkaart)) {
            System.out.println("OVChipkaart saved!");
        } else {
            System.out.println("OVChipkaart failed to save!");
        }
        System.out.println();

        // Add product to OVChipkaart
        System.out.println("Adding products to OVChipkaart: ");
        if (productDAO.addOVChipkaart(product, ovChipkaart, "actief")) {
            System.out.println("Success!");
        } else {
            System.out.println("Failed!");
        }

        if (productDAO.addOVChipkaart(product2, ovChipkaart, "actief")) {
            System.out.println("Success!");
        } else {
            System.out.println("Failed!");
        }

        // Find product by OVChipkaart
        System.out.println("Finding product for OVChipkaart with kaartnummer " + ovChipkaart.getId() + ": ");
        System.out.println(productDAO.findByOVChipkaart(ovChipkaart));
        System.out.println();

        // Find all products
        System.out.println("Finding all products: ");
        System.out.println(productDAO.findAll());
        System.out.println();

        // Delete products
        System.out.println("Deleting products with cascade: ");
        if (productDAO.delete(product)) {
            System.out.println("Product 1 deleted!");
        } else {
            System.out.println("Product 1 failed to delete!");
        }
        if (productDAO.delete(product2)) {
            System.out.println("Product 2 deleted!");
        } else {
            System.out.println("Product 2 failed to delete!");
        }

        if (ovChipkaartDAO.delete(ovChipkaart)) {
            System.out.println("OVChipkaart deleted!");
        } else {
            System.out.println("OVChipkaart failed to delete!");
        }
        if (reizigerDAO.delete(reiziger)) {
            System.out.println("Reiziger deleted!");
        } else {
            System.out.println("Reiziger failed to delete!");
        }
    }

}
