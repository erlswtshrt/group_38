package group38.elderlyportal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.GregorianCalendar;

/**
 * Created by JohnEarle on 3/29/15.
 */
public class Dose implements Parcelable {

    //fields
    private int time ;
    private int numOfPills ;

    //constructor to set all fields
    public Dose (int time, int numOfPills) {
        this.time = time ;
        this.numOfPills = numOfPills ;
    }

    //constructor to be used for parseling
    protected Dose(Parcel in) {
        time = in.readInt();
        numOfPills = in.readInt();
    }

    public void setTime (int time) {
        this.time = time ;
    }

    public void setNumOfPills (int numOfPills) {
        this.numOfPills = numOfPills ;
    }

    public int getTime () {
        return this.time ;
    }

    public int getNumOfPills () {
        return this.numOfPills ;
    }

    @Override
    public int describeContents() {
        return 0 ;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue (time) ;
        dest.writeInt (numOfPills) ;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Dose> CREATOR = new Parcelable.Creator<Dose>() {
        @Override
        public Dose createFromParcel(Parcel in) {
            return new Dose(in) ;
        }

        @Override
        public Dose[] newArray(int size) {
            return new Dose [size] ;
        }
    } ;
}
