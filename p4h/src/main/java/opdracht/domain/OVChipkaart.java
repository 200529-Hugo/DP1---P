package opdracht.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    // Fields
    @Id
    @Column(name = "kaart_nummer")
    private int kaartnummer;
    @Column(name = "geldig_tot")
    private Date geldigTot;
    private int klasse;
    private double saldo;
    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    // Constructors
    public OVChipkaart() {
    }

    public OVChipkaart(int kaartnummer, Date geldigTot, int klasse, double saldo, Reiziger reiziger) {
        this.kaartnummer = kaartnummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    // Getters and setters methods
    public int getKaartnummer() {
        return kaartnummer;
    }

    public void setKaartnummer(int kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
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

    // toString method
    @Override
    public String toString() {
        return kaartnummer +
                " is geldig tot " + String.format("%tF", geldigTot) +
                " met klasse " + klasse +
                " heeft saldo " + saldo +
                " en is van reiziger " + reiziger.getNaam() +
                "\n";
    }
}

