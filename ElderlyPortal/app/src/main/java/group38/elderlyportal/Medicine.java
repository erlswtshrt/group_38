package group38.elderlyportal;

import java.util.GregorianCalendar;

/**
 * Created by Scott on 3/26/2015.
 */
public class Medicine {

    //fields
    private String name ;
    private GregorianCalendar dateOfNextRefill ;
    private String dosage ;

    //constructor to set all fields
    public Medicine (String name, GregorianCalendar dateOfNextRefill, String dosage) {
        this.name = name ;
        this.dateOfNextRefill = dateOfNextRefill ;
        this.dosage = dosage ;
    }

    //simple constructor, all fields are null to begin with
    public Medicine () {

    }

    public void setName (String name) {
        this.name = name ;
    }

    public void setDateOfNextRefill (GregorianCalendar dateOfNextRefill) {
        this.dateOfNextRefill = dateOfNextRefill ;
    }

    public void setDosage (String dosage) {
        this.dosage = dosage ;
    }

    public String getName () {
        return this.name ;
    }

    public GregorianCalendar getDateOfNextRefill () {
        return this.dateOfNextRefill ;
    }

    public String getDosage () {
        return this.dosage ;
    }

    @Override
    public String toString () {
        return name + " " + "NEXT REFILL" + " " + dosage ;
    }

}
