package project.domain;

import java.util.Date;

public class Reiziger {
    // Fields
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    // Constructor
    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    // Getters and Setters
    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return (tussenvoegsel == null || tussenvoegsel.isEmpty())
                ? voorletters + " " + achternaam
                : voorletters + " " + tussenvoegsel + " " + achternaam;
    }

    // Methods
    @Override
    public String toString() {
        return getNaam() + " (" + geboortedatum + ")\n";
    }
}

