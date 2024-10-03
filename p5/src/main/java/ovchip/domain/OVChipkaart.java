package ovchip.domain;

import java.sql.Date;

public class OVChipkaart {
    // Attributes
    private int id;
    private Date geldigTot;
    private int klasse;
    private double saldo;
    private Reiziger reiziger;

    // Constructors
    public OVChipkaart(int id, Date geldigTot, int klasse, double saldo, Reiziger reiziger) {
        this.id = id;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    @Override
    public String toString() {
        return "Kaartnummer: " + id + ", geldig tot: " + geldigTot + ", klasse: " + klasse + ", saldo: " + saldo + ", reiziger: " + reiziger.getNaam() + "\n";
    }
}

