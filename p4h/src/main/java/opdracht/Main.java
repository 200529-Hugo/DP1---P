package opdracht;

import opdracht.dao.AdresDAO;
import opdracht.dao.OVChipkaartDAO;
import opdracht.dao.ReizigerDAO;
import opdracht.daopSql.AdresDAOPsql;
import opdracht.daopSql.OVChipkaartDAOPsql;
import opdracht.daopSql.ReizigerDAOPsql;
import opdracht.domain.Adres;
import opdracht.domain.OVChipkaart;
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
        AdresDAO adresDAO = new AdresDAOPsql(em);
        OVChipkaartDAO ovChipkaartDAO = new OVChipkaartDAOPsql(em);

        // Test DAO's
        testReizigerDAO(reizigerDAO);
        testAdresDAO(adresDAO, reizigerDAO);
        testOVChipkaartDAO(ovChipkaartDAO, reizigerDAO);

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
        Reiziger sietske = new Reiziger(6, "S", null, "Boers", java.sql.Date.valueOf("1999-08-11"));
        System.out.print("Reiziger sietske toevoegen: ");
        if (rdao.save(sietske)) {
            System.out.println("Gelukt!");
            System.out.println("Reiziger sietske: " + sietske);
        } else {
            System.out.println("Mislukt!");
        }
        System.out.println();

        // Update the new reiziger in the database
        sietske.setAchternaam("Boersma");
        System.out.print("Reiziger sietske updaten: ");
        if (rdao.update(sietske)) {
            System.out.println("Gelukt!");
            System.out.println("Reiziger sietske: " + sietske);
        } else {
            System.out.println("Mislukt!");
        }
        System.out.println();

        // Delete the new reiziger from the database
        System.out.print("Reiziger sietske verwijderen: ");
        if (rdao.delete(sietske)) {
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
        Reiziger sietske = new Reiziger(6, "S", null, "Boers", java.sql.Date.valueOf("1999-08-11"));
        rdao.save(sietske);
        Adres sietskeAdres = new Adres(6, "1234AB", "1", "Straatweg", "Utrecht", sietske);
        System.out.print("Adres sietske toevoegen: ");
        if (adao.save(sietskeAdres)) {
            System.out.println("Gelukt!");
            System.out.println("Adres sietske: " + sietskeAdres);
        } else {
            System.out.println("Mislukt!");
        }
        System.out.println();

        // Update the new address in the database
        sietskeAdres.setHuisnummer("2");
        System.out.print("Adres sietske updaten: ");
        if (adao.update(sietskeAdres)) {
            System.out.println("Gelukt!");
            System.out.println("Adres sietske: " + sietskeAdres);
        } else {
            System.out.println("Mislukt!");
        }
        System.out.println();

        // Delete the new address from the database
        System.out.print("Adres sietske verwijderen: ");
        if (adao.delete(sietskeAdres)) {
            System.out.println("Gelukt!");
        } else {
            System.out.println("Mislukt!");
        }
        rdao.delete(sietske);
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
}
