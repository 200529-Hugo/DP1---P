package project;

import project.domain.Reiziger;
import project.domain.Adres;
import project.dao.AdresDAO;
import project.dao.ReizigerDAO;
import project.daopsql.AdresDAOPsql;
import project.daopsql.ReizigerDAOPsql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        getConnection();

        // Create DAO's
        ReizigerDAO reizigerDAO = new ReizigerDAOPsql(connection);
        AdresDAO adresDAO = new AdresDAOPsql(connection, reizigerDAO);

        // Test DAO's
        testReizigerDAO(reizigerDAO);
        testAdresDAO(adresDAO, reizigerDAO);

        closeConnection();
    }

    public static void getConnection() throws SQLException {
        // Change the url to your own database
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
}
