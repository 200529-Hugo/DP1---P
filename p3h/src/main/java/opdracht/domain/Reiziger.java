package opdracht.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Reiziger {
    // Fields
    @Id
    @Column(name = "reiziger_id")
    private int id;

    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;

    @Temporal(TemporalType.DATE)
    private Date geboortedatum;

    @OneToOne(mappedBy = "reiziger", cascade = CascadeType.ALL)
    private Adres adres;

    // Constructors
    public Reiziger() {
    }

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    // Getters and setters methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getNaam() {
        String tsnvgsl;
        if (tussenvoegsel == null) {
            tsnvgsl = "";
        } else {
            tsnvgsl = tussenvoegsel + " ";
        }
        return voorletters + " " + tsnvgsl + achternaam;
    }

    // toString method
    @Override
    public String toString() {
        String adresString;
        if (adres == null) {
            adresString = "geen";
        } else {
            adresString = adres.toString();
        }
        return this.getNaam() + " (" + geboortedatum + ") \n adres: " + adresString + "\n";
    }
}

