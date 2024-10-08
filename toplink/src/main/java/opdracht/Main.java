package opdracht;

import opdracht.dao.AdresDAO;
import opdracht.dao.OVchipkaartDAO;
import opdracht.dao.ProductDAO;
import opdracht.dao.ReizigerDAO;
import opdracht.daop.AdresDAOH;
import opdracht.daop.OVchipkaartDAOH;
import opdracht.daop.ProductDAOH;
import opdracht.daop.ReizigerDAOH;
import opdracht.domain.Adres;
import opdracht.domain.OVchipkaart;
import opdracht.domain.Product;
import opdracht.domain.Reiziger;

import javax.persistence.*;
import java.sql.Date;

public class Main {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ovchipkaartPU");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        ReizigerDAO rdao = new ReizigerDAOH(em);
        AdresDAO adao = new AdresDAOH(em);
        OVchipkaartDAO ovdao = new OVchipkaartDAOH(em);
        ProductDAO pdao = new ProductDAOH(em);

        testReizigerDAO(rdao);
        testAdresDAO(adao, rdao);
        testOVchipkaartDAO(ovdao, rdao);
        testProductDAO(pdao, ovdao);


        em.close();
    }

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
        for (Reiziger r : rdao.findByGbdatum(Date.valueOf("2002-10-22"))) {
            System.out.println(r);
        }
        System.out.println();
    }

    public static void testAdresDAO(AdresDAO adao, ReizigerDAO rdao) {
        // Get all addresses from the database
        System.out.println("---- Test AdresDAO ----");
        System.out.println("Alle adressen: ");
        for (Adres a : adao.findAll()) {
            System.out.println(a);
        }
        System.out.println();

        // Get address with id 2
        System.out.println("Adres met id 2: ");
        System.out.println(adao.findById(2));
        System.out.println();

        // Get addresses of reiziger with id 2
        System.out.println("Adressen van reiziger met id 3: ");
        for (Adres a : adao.findByReiziger(rdao.findById(3))) {
            System.out.println(a);
        }
        System.out.println();
    }

    public static void testOVchipkaartDAO(OVchipkaartDAO ovdao, ReizigerDAO rdao) {
        // Get all OV chipkaarten from the database
        System.out.println("---- Test OVchipkaartDAO ----");
        System.out.println("Alle OV chipkaarten: ");
        for (OVchipkaart o : ovdao.findAll()) {
            System.out.println(o);
        }
        System.out.println();

        // Get OV chipkaart with id 35283
        System.out.println("OV chipkaart met id 35283: ");
        System.out.println(ovdao.findById(35283));
        System.out.println();

        // Get OV chipkaarten of reiziger with id 3
        System.out.println("OV chipkaarten van reiziger met id 3: ");
        for (OVchipkaart o : ovdao.findByReiziger(rdao.findById(3))) {
            System.out.println(o);
        }
        System.out.println();
    }

    public static void testProductDAO(ProductDAO pdao, OVchipkaartDAO ovdao) {
        // Get all products from the database
        System.out.println("---- Test ProductDAO ----");
        System.out.println("Alle producten: ");
        for (Product p : pdao.findAll()) {
            System.out.println(p);
        }
        System.out.println();

        // Get product with id 1
        System.out.println("Product met id 2: ");
        System.out.println(pdao.findById(2));
        System.out.println();

        // Get products of OV chipkaart with id 35283
        System.out.println("Producten van OV chipkaart met id 35283: ");
        for (Product p : pdao.findByOVChipkaart(ovdao.findById(35283))) {
            System.out.println(p);
        }
        System.out.println();
    }


}