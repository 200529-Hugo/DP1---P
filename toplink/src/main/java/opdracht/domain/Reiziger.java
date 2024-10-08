package opdracht.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reiziger")
public class Reiziger {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reiziger_seq")
    @SequenceGenerator(name = "reiziger_seq", sequenceName = "reiziger_sequence", allocationSize = 1)
    @Column(name = "reiziger_id")
    private Integer reizigerId;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    public Reiziger() {
    }

    public Reiziger(String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public Integer getId() {
        return reizigerId;
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
        return "Reiziger " + getNaam() + " (" + geboortedatum + ")";
    }
}
