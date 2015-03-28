package group38.elderlyportal;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.GregorianCalendar;
/**
 * Created by Scott on 3/26/2015.
 */
public class Medicine implements Parcelable {

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

    //constructor to be used for parseling
    protected Medicine(Parcel in) {
        name = in.readString();
        dateOfNextRefill = (GregorianCalendar) in.readValue(GregorianCalendar.class.getClassLoader());
        dosage = in.readString();
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
        return name + " " + "NEXT REFILL" + " " + dosage;
    }

    @Override
    public int describeContents() {
        return 0 ;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString (name) ;
        dest.writeValue (dateOfNextRefill) ;
        dest.writeString (dosage) ;
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