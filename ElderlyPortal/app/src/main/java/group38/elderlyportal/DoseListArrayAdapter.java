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
        super(context, R.layout.dose_list_row, values) ;
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

        Integer time = dose.getTime();

        TextView timeText = (TextView) rowView.findViewById(R.id.timeText);
        timeText.setText("Time: " + convertTime(time));

        Integer numOfPills = dose.getNumOfPills() + 1;
        String numOfPillsStr = numOfPills.toString();

        TextView numOfPillsText = (TextView) rowView.findViewById(R.id.numOfPillsText);
        numOfPillsText.setText("Number of Pills: " + numOfPillsStr);

        //set the button's tag so that the button knows its dose
        Button deleteButton = (Button) rowView.findViewById(R.id.deleteButton) ;
        deleteButton.setTag (dose) ;

        return rowView;
    }

    // convert from int representing hour to string representing time
    public static String convertTime(int time) {
        String answer = "";
        switch(time) {
            case 0: answer="12:00 AM";
                    break;
            case 1: answer="1:00 AM";
                break;
            case 2: answer="2:00 AM";
                break;
            case 3: answer="3:00 AM";
                break;
            case 4: answer="4:00 AM";
                break;
            case 5: answer="5:00 AM";
                break;
            case 6: answer="6:00 AM";
                break;
            case 7: answer="7:00 AM";
                break;
            case 8: answer="8:00 AM";
                break;
            case 9: answer="9:00 AM";
                break;
            case 10: answer="10:00 AM";
                break;
            case 11: answer="11:00 AM";
                break;
            case 12: answer="12:00 PM";
                break;
            case 13: answer="1:00 PM";
                break;
            case 14: answer="2:00 PM";
                break;
            case 15: answer="3:00 PM";
                break;
            case 16: answer="4:00 PM";
                break;
            case 17: answer="5:00 PM";
                break;
            case 18: answer="6:00 PM";
                break;
            case 19: answer="7:00 PM";
                break;
            case 20: answer="8:00 PM";
                break;
            case 21: answer="9:00 PM";
                break;
            case 22: answer="10:00 PM";
                break;
            case 23: answer="11:00 PM";
                break;
        }
        return answer;
    }
}
