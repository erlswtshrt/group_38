
package group38.elderlyportal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import android.view.*;
import android.widget.*;
import java.util.* ;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ListActivity;

public class durr extends ListActivity {
    private TextView text; //text above the list

    //IDs for new activities
    public static final int AddAMedicationActivity_ID = 1;
    public static final int EditAMedicationActivity_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medications);

        //populate the medicine list with random medicines - for debugging
        Medicine med1 = new Medicine ("medicine 1", new GregorianCalendar (2015,3,26,3,1), "1 dose") ;
        Medicine med2 = new Medicine ("medicine 2", new GregorianCalendar (2015,3,26,5,0), "3 doses") ;
        Medicine med3 = new Medicine ("medicine 3", new GregorianCalendar (2015,9,2,12,47), "9 doses") ;
        Medicine med4 = new Medicine ("medicine 4", new GregorianCalendar (2015,3,28,8,9), "4 doses") ;
        Medicine med5 = new Medicine ("medicine 5", new GregorianCalendar (2015,12,28,2,26), "2 doses") ;
        Medicine med6 = new Medicine ("medicine 6", new GregorianCalendar (2015,3,27,7,47), "3 doses") ;
        Medicine med7 = new Medicine ("medicine 7", new GregorianCalendar (2014,3,28,8,9), "10 doses") ;
        Medicine med8 = new Medicine ("medicine 8", new GregorianCalendar (2015,3,28,11,26), "0 doses") ;
        Medicine[] list = new Medicine[] {med1, med2, med3, med4, med5, med6, med7, med8} ;

        // initiate the listadapter to be used for managing the list of medicines
        MedicineListArrayAdapter adapter = new MedicineListArrayAdapter(this, list);

        // assign the list adapter to this class
        setListAdapter(adapter);

        // assign the spinner's listener so it communicates to the list adapter
        Spinner spinner = (Spinner) findViewById(R.id.spinner) ;
        spinner.setOnItemSelectedListener(new SpinnerListener(adapter)) ;
    }

    /* Spinner listener class that fine tunes the spinner's actions.
       Specifically, when an option of the spinner is selected,
       the spinner tells the list adapter to sort by that string.
     */
    private class SpinnerListener implements AdapterView.OnItemSelectedListener {

        private MedicineListArrayAdapter adapter ; //the adapter

        /* constructor stores a reference to the list adapter
         */
        public SpinnerListener (MedicineListArrayAdapter adapter) {
            super () ;
            this.adapter = adapter ;
        }

        /* When an item is selected, the spinner has the adapter sort by it.
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Spinner spinner = (Spinner) parent ;
            String selected = spinner.getSelectedItem().toString() ;
            adapter.sortBy (selected) ;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    /* When an item in the list is clicked, have it use an intent to start an
       edit medicine activity. The intent is given the Medicine that was clicked.
    */
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        //determine the selected medicine
        super.onListItemClick(list, view, position, id);
        Medicine selectedItem = (Medicine) getListView().getItemAtPosition(position);

        //set the top text - for debuggin
        text.setText("You clicked " + selectedItem.getName() + " at position " + position);

        //create intent, store medicine, start intent
        Intent intent = new Intent (this, EditAMedicationActivity.class) ;
        intent.putExtra("Medicine", selectedItem) ;
        startActivityForResult (intent, EditAMedicationActivity_ID) ;
    }

    /* Starts the add a medicine activity.
       This action is performed by the add a medicine button.
     */
    public void addAMedication (View view) {
        Intent intent = new Intent (this, AddAMedicationActivity.class) ;
        startActivityForResult(intent, AddAMedicationActivity_ID) ;
    }

    /* A test method to be used for debugging.
       @TODO remove this
     */
    public void test (View view) {
        MedicineListArrayAdapter adapter = (MedicineListArrayAdapter) getListAdapter() ;
        double rand = Math.random() ;
        if (rand < 0.5) adapter.showBy ("Date") ;
        if (rand > 0.5) adapter.showBy ("Dosage") ;
    }

    public class MedicineListArrayAdapter extends ArrayAdapter<Medicine> {
        private final Context context;
        private final Medicine[] values;
        private String showBy ;

        public MedicineListArrayAdapter(Context context, Medicine[] values) {
            super(context, R.layout.medicine_list_row, values);
            this.context = context;
            this.values = values;
            this.showBy = "Date" ;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.medicine_list_row, parent, false);
            TextView textView1 = (TextView) rowView.findViewById(R.id.label1);
            TextView textView2 = (TextView) rowView.findViewById(R.id.label2);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            textView1.setText(values[position].getName());

            if (showBy.equals("Date")) {
                textView2.setText(formatGregorianCalendar(values[position].getDateOfNextRefill()));
            }
            else if (showBy.equals("Dosage")) {
                textView2.setText(values[position].getDosage());
            }

            String s = values[position].getName();
            imageView.setImageResource(R.drawable.ic_launcher);

            return rowView;
        }

        public void showBy (String showBy) {
            this.showBy = showBy ;
            this.notifyDataSetChanged();
        }

        public void sortBy (String sortBy) {
            if (sortBy.equals("Name")) {
                this.sort(new Comparator<Medicine>() {
                    @Override
                    public int compare(Medicine m1, Medicine m2) {
                        return m1.getName().compareTo(m2.getName());
                    }
                });
            }
            else if (sortBy.equals("Date")) {
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
            int year = gc.get(GregorianCalendar.YEAR);
            int month = gc.get(GregorianCalendar.MONTH);
            int day = gc.get(GregorianCalendar.DAY_OF_MONTH);
            int hour = gc.get(GregorianCalendar.HOUR_OF_DAY);
            int minute = gc.get(GregorianCalendar.MINUTE);
            return String.format("%02d", month) + "/" +
                    String.format("%02d", day) + "/" +
                    String.format("%04d", year) + " at " +
                    String.format("%02d", hour) + ":" +
                    String.format("%02d", minute);
        }
    }

}