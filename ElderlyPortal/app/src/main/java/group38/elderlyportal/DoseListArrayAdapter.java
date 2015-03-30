package group38.elderlyportal;

import android.content.Context;
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

/**
 * Created by JohnEarle on 3/29/15.
 */
/* This class is an array adapter meant to manage the medicine list.
   This includes sorting the list and remembering what fields to show
   in each row.
 */
public class DoseListArrayAdapter extends ArrayAdapter<Dose> {
    private final Context context;
    private final ArrayList<Dose> values; //the medicines to be displayed
    private String showBy ; //the field that the second line of the row should display
    private final int DELETE_BUTTON_ROW_TAG_KEY = 1 ; //the int of the tag where the button stores what row it's in

    /* Constructor, which takes in the list of medicines.
       Sets initial second line paramter to "Data"
     */
    public DoseListArrayAdapter(Context context, ArrayList<Dose> values) {
        super(context, R.layout.medicine_list_row, values) ;
        this.context = context ;
        this.values = values ;
    }

    /* Method that gets the View associated with each row and displaying it properly.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.dose_list_row, parent, false);
        Dose dose = values.get(position) ;

        return rowView;
    }
}
