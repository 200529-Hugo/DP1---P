package ovchip.domain;

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

    @OneToMany(mappedBy = "reiziger", cascade = CascadeType.ALL)
    private List<OVChipkaart> ovChipkaarten;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return (tussenvoegsel == null || tussenvoegsel.isEmpty())
                ? voorletters + " " + achternaam
                : voorletters + " " + tussenvoegsel + " " + achternaam;
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

    @Override
    public String toString() {
        return getNaam() + " (" + geboortedatum + ")\n";
    }
}

