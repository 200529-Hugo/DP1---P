package opdracht;

import opdracht.dao.ReizigerDAO;
import opdracht.daopSql.ReizigerDAOPsql;
import opdracht.domain.Reiziger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    // Create an EntityManagerFactory when the application is started
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ovchipkaartPU");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        // Create DAO's
        ReizigerDAO reizigerDAO = new ReizigerDAOPsql(em);

        // Test DAO's
        testReizigerDAO(reizigerDAO);

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
        Reiziger hugo = new Reiziger(6, "HAJ", "de", "Heus", java.sql.Date.valueOf("2004-02-23"));
        System.out.print("Reiziger Hugo toevoegen: ");
        if (rdao.save(hugo)) {
            System.out.println("Gelukt!");
            System.out.println("Reiziger Hugo: " + hugo);
        } else {
            System.out.println("Mislukt!");
        }
        System.out.println();

        // Update the new reiziger in the database
        hugo.setAchternaam("Groot");
        System.out.print("Reiziger Hugo updaten: ");
        if (rdao.update(hugo)) {
            System.out.println("Gelukt!");
            System.out.println("Reiziger sietske: " + hugo);
        } else {
            System.out.println("Mislukt!");
        }
        System.out.println();

        // Delete the new reiziger from the database
        System.out.print("Reiziger Hugo verwijderen: ");
        if (rdao.delete(hugo)) {
            System.out.println("Gelukt!");
        } else {
            System.out.println("Mislukt!");
        }
        System.out.println();
    }
}
