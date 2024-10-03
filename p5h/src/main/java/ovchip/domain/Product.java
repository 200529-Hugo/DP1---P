package ovchip.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    // Fields
    @Id
    @Column(name = "product_nummer")
    private int productNummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ov_chipkaart_product",
            joinColumns = {@JoinColumn(name = "product_nummer")},
            inverseJoinColumns = {@JoinColumn(name = "kaart_nummer")}
    )
    private List<OVChipkaart> ovChipkaarten = new ArrayList<>();

    // Constructors
    public Product() {
    }

    public Product(int productNummer, String naam, String beschrijving, double prijs) {
        this.productNummer = productNummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    // Getters and setters
    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    @Override
    public String toString() {
        return "Product: " + naam + " (" + productNummer + ") - " + beschrijving + " - €" + prijs + "\n";
    }
}
