package opdracht.domain;

import javax.persistence.*;

@Entity
@Table(name = "ov_chipkaart")
public class OVchipkaart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ov_chipkaart_seq")
    @SequenceGenerator(name = "ov_chipkaart_seq", sequenceName = "ov_chipkaart_sequence", allocationSize = 1)
    @Column(name = "kaart_nummer")
    private Integer kaartNummer;
    @Column(name = "geldig_tot")
    private String geldigTot;
    private int klasse;
    private double saldo;
    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    public OVchipkaart() {
    }

    public OVchipkaart(String geldigTot, int klasse, double saldo, Reiziger reiziger) {
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    public Integer getKaartNummer() {
        return kaartNummer;
    }

    public String getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(String geldigTot) {
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

    @Override
    public String toString() {
        return kaartNummer + " " + geldigTot + " " + klasse + " " + saldo + " " + reiziger.getNaam();
    }
}
