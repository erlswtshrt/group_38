package group38.elderlyportal;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;

import group38.elderlyportal.Medicine;
import group38.elderlyportal.R;

/* This class is an array adapter meant to manage the medicine list.
   This includes sorting the list and remembering what fields to show
   in each row.
 */
public class MedicineListArrayAdapter extends ArrayAdapter<Medicine> {
    private final Context context;
    private final ArrayList<Medicine> values; //the medicines to be displayed
    private String showBy ; //the field that the second line of the row should display
    private final String INITAL_SHOW_BY = "Date" ; //start off by showing the date
    private final int DELETE_BUTTON_ROW_TAG_KEY = 1 ; //the int of the tag where the button stores what row it's in

    /* Constructor, which takes in the list of medicines.
       Sets initial second line paramter to "Data"
     */
    public MedicineListArrayAdapter(Context context, ArrayList<Medicine> values) {
        super(context, R.layout.medicine_list_row, values) ;
        this.context = context ;
        this.values = values ;
        this.showBy = INITAL_SHOW_BY ;
    }

    /* Method that gets the View associated with each row and displaying it properly.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.medicine_list_row, parent, false);
        Medicine medicine = values.get(position) ;

        //set the image
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon) ;
        imageView.setImageResource(R.drawable.ic_launcher) ;

        //set the first line
        TextView line1TextView = (TextView) rowView.findViewById(R.id.label1);
        line1TextView.setText(medicine.getName());

        //set the second line, conditioning on showBy
        TextView textView2 = (TextView) rowView.findViewById(R.id.label2);
        if (showBy.equals("Date")) {
            textView2.setText(formatGregorianCalendar(medicine.getDateOfNextRefill()));
        }
        else if (showBy.equals("Dosage")) {
            textView2.setText(medicine.getDosage());
        }

        //set the button's tag so that the button knows its medicine
        Button deleteButton = (Button) rowView.findViewById(R.id.deleteButton) ;
        deleteButton.setTag (medicine) ;

        Button editButton = (Button) rowView.findViewById(R.id.editButton);
        editButton.setTag(medicine);
        return rowView;
    }

    /* Determines the new medicine field to show for the second line.
       Updates the showBy field and then tells the list to refresh.
     */
    public void showBy (String showBy) {
        this.showBy = showBy ;
        this.notifyDataSetChanged();
    }

    /* Determines the new medicine field to sort by.
       Calls a sorting method with a comparator specific to that field,
       which automatically refreshes the list.
     */
    public void sortBy (String sortBy) {
        if (sortBy.equals("Name")) {
            this.sort(new Comparator<Medicine>() {
                @Override
                public int compare(Medicine m1, Medicine m2) {
                return m1.getName().compareTo(m2.getName());
                }
            });
        } else if (sortBy.equals("Date")) {
            this.sort(new Comparator<Medicine>() {
                @Override
                public int compare(Medicine m1, Medicine m2) {
                return m1.getDateOfNextRefill().compareTo(m2.getDateOfNextRefill());
                }
            });
        }
    }


    /* How to format a GregorianCalendar so that it looks good for displaying.
       To be used on the second line of each list row.
       The date is of the form: "MM/DD/YYY at hh:mm"
    */
    private String formatGregorianCalendar (GregorianCalendar gc) {
        int year = gc.get(GregorianCalendar.YEAR) ;
        int month = gc.get(GregorianCalendar.MONTH) ;
        int day = gc.get(GregorianCalendar.DAY_OF_MONTH) ;
        int hour = gc.get(GregorianCalendar.HOUR_OF_DAY) ;
        int minute = gc.get(GregorianCalendar.MINUTE) ;
        return String.format("%02d",month) + "/" +
                String.format("%02d",day) + "/" +
                String.format("%04d",year) + " at " +
                String.format("%02d",hour) + ":" +
                String.format("%02d",minute) ;
    }

}