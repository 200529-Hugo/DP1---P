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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;

public class Main {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        getConnection();

        // Create DAO's
        ReizigerDAO reizigerDAO = new ReizigerDAOPsql(connection);
        AdresDAO adresDAO = new AdresDAOPsql(connection, reizigerDAO);
        OVChipkaartDAO ovChipkaartDAO = new OVChipkaartDAOPsql(connection);
        ProductDAO productDAO = new ProductDAOPsql(connection);

        // Test DAO's
        testReizigerDAO(reizigerDAO);
        testAdresDAO(adresDAO, reizigerDAO);
        testOVChipkaartDAO(ovChipkaartDAO, reizigerDAO);
        testProductDAO(productDAO, ovChipkaartDAO, reizigerDAO);

        closeConnection();
    }

    public static void getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/ovchip?user=hugodeheus&password=root";
        connection = DriverManager.getConnection(url);
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Test the ReizigerDAO
    public static void testReizigerDAO(ReizigerDAO reizigerDAO) {
        System.out.println("---- Test ReizigerDAO ----");

        // Create a new Reiziger
        Reiziger reiziger = new Reiziger(6, "H", "de", "Heus", Date.valueOf("2004-02-23"));
        System.out.print("Saving reiziger: ");
        if (reizigerDAO.save(reiziger)) {
            System.out.println("Success!");
            System.out.println(reiziger);
        } else {
            System.out.println("Failed!");
        }
        System.out.println();

        // Update reiziger
        reiziger.setVoorletters("H.A.J.");
        System.out.print("Updating reiziger: ");
        if (reizigerDAO.update(reiziger)) {
            System.out.println("Success!");
            System.out.println(reiziger);
        } else {
            System.out.println("Failed!");
        }
        System.out.println();

        // Find reiziger by id
        System.out.println("Finding reiziger with id 6: ");
        System.out.println(reizigerDAO.findById(6));
        System.out.println();

        // Find reiziger by geboortedatum
        System.out.println("Finding reiziger with geboortedatum 2004-02-23: ");
        System.out.println(reizigerDAO.findByGbdatum("2004-02-23"));
        System.out.println();

        // Find all reizigers
        System.out.println("Finding all reizigers: ");
        System.out.println(reizigerDAO.findAll());
        System.out.println();

        // Delete reiziger
        System.out.print("Deleting reiziger: ");
        if (reizigerDAO.delete(reiziger)) {
            System.out.println("Success!");
        } else {
            System.out.println("Failed!");
        }
        System.out.println();
    }

    // Test the AdresDAO
    public static void testAdresDAO(AdresDAO adresDAO, ReizigerDAO reizigerDAO) {
        System.out.println("---- Test AdresDAO ----");

        // Create a new Reiziger and Adres
        Reiziger reiziger = new Reiziger(6, "H", "de", "Heus", Date.valueOf("2004-02-23"));
        reizigerDAO.save(reiziger);
        Adres adres = new Adres(6, "1234AB", "1", "Straat", "Woonplaats", reiziger);
        System.out.print("Saving adres: ");
        if (adresDAO.save(adres)) {
            System.out.println("Success!");
            System.out.println(adres);
        } else {
            System.out.println("Failed!");
        }
        System.out.println();

        // Update adres
        adres.setPostcode("4321BA");
        System.out.print("Updating adres: ");
        if (adresDAO.update(adres)) {
            System.out.println("Success!");
            System.out.println(adres);
        } else {
            System.out.println("Failed!");
        }
        System.out.println();

        // Find adres by reiziger
        System.out.println("Finding adres for reiziger with id 6: ");
        System.out.println(adresDAO.findByReiziger(reiziger));
        System.out.println();

        // Find all adres
        System.out.println("Finding all addresses: ");
        System.out.println(adresDAO.findAll());
        System.out.println();

        // Delete adres
        System.out.print("Deleting adres: ");
        if (adresDAO.delete(adres)) {
            System.out.println("Success!");
        } else {
            System.out.println("Failed!");
        }
        reizigerDAO.delete(reiziger);
        System.out.println();
    }

    // Test the OVChipkaartDAO
    public static void testOVChipkaartDAO(OVChipkaartDAO ovChipkaartDAO, ReizigerDAO reizigerDAO) {
        System.out.println("---- Test OVChipkaartDAO ----");

        // Find OVChipkaart by reiziger
        Reiziger reiziger = reizigerDAO.findById(2);
        System.out.println("Finding OVChipkaarten for reiziger with id 2: ");
        System.out.println(ovChipkaartDAO.findByReiziger(reiziger));
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
        System.out.print("Adding products to OVChipkaart: ");
        if (productDAO.addOVChipkaart(product, ovChipkaart, "actief")) {
            System.out.println("Product 1 added!");
        } else {
            System.out.println("Failed!");
        }

        if (productDAO.addOVChipkaart(product2, ovChipkaart, "actief")) {
            System.out.println("Product 2 added!");
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
