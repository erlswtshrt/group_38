package group38.elderlyportal;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.GregorianCalendar;
/**
 * Created by Scott on 3/26/2015.
 */
public class Medicine implements Parcelable {

    //fields
    private ArrayList<Dose> doses;
    private String brandName;
    private String scientificName;
    private String instructions;
    private String numRefills;
    private GregorianCalendar refillDate;

    //constructor to set all fields
    public Medicine (ArrayList<Dose> doses, String brandName, String scientificName, String instructions, String numRefills, GregorianCalendar refillDate) {
        this.doses = doses;
        this.brandName = brandName;
        this.scientificName = scientificName;
        this.instructions = instructions;
        this.numRefills = numRefills;
        this.refillDate = refillDate;
    }
    //simple constructor, all fields are null to begin with
    public Medicine () {
    }

    //constructor to be used for parseling
    protected Medicine(Parcel in) {
        doses = (ArrayList<Dose>) in.readArrayList(ArrayList.class.getClassLoader());
        brandName = in.readString();
        scientificName = in.readString();
        instructions = in.readString();
        instructions = in.readString();
        numRefills = in.readString();
        refillDate = (GregorianCalendar) in.readValue(GregorianCalendar.class.getClassLoader());
    }

    public void setBrandName (String brandName) {
        this.brandName = brandName ;
    }

    public void setScientificName (String scientificName) {
        this.scientificName = scientificName ;
    }

    public void setInstructions (String instructions) {
        this.instructions = instructions ;
    }

    public void setNumRefills (String numRefills) {
        this.numRefills = numRefills ;
    }

    public void setDateOfNextRefill (GregorianCalendar refillDate) {
        this.refillDate = refillDate ;
    }

    public void setDoses (ArrayList<Dose> doses) {
        this.doses = doses ;
    }


    // getter methods

    public String getBrandName () {
        return brandName ;
    }

    public String getScientificName () {
        return scientificName ;
    }

    public String getInstructions () {
        return instructions ;
    }

    public String getNumRefills () {
        return numRefills ;
    }

    public GregorianCalendar getDateOfNextRefill () {
        return refillDate ;
    }

    public ArrayList<Dose> getDoses () {
        return doses ;
    }

    @Override
    public String toString () {
        return brandName + " " + "NEXT REFILL";
    }

    @Override
    public int describeContents() {
        return 0 ;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(doses);
        dest.writeString (brandName);
        dest.writeString (scientificName);
        dest.writeString (instructions);
        dest.writeString (numRefills);
        dest.writeValue (refillDate) ;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Medicine> CREATOR = new Parcelable.Creator<Medicine>() {
        @Override
        public Medicine createFromParcel(Parcel in) {
            return new Medicine(in) ;
        }

        @Override
        public Medicine[] newArray(int size) {
            return new Medicine [size] ;
        }
    } ;
}