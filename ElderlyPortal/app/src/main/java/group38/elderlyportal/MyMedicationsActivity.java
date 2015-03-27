
package group38.elderlyportal;

import android.app.Activity;
import android.content.Context;
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
/*
public class MyMedicationsActivity extends ListActivity {

    MySimpleArrayAdapter adapter ;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //setContentView (R.layout.activity_my_medications) ;

        Medicine med1 = new Medicine ("medicine 2", new GregorianCalendar(), "Monday") ;
        Medicine med2 = new Medicine ("medicine 3", new GregorianCalendar(), "Tuesday") ;
        Medicine med3 = new Medicine ("medicine 4", new GregorianCalendar(), "Wednesday") ;

        Medicine[] list = new Medicine[] {med1, med2, med3} ;
        adapter = new MySimpleArrayAdapter(this, list);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Medicine item = (Medicine) getListAdapter().getItem(position);
        Toast.makeText(this, item.getName() + " selected", Toast.LENGTH_LONG).show();
    }

    public void sort1 () {
        Toast.makeText (this, "Sort1", Toast.LENGTH_LONG).show();
        adapter.sort(new Comparator<Medicine>() {
            @Override
            public int compare(Medicine m1, Medicine m2) {
                return m1.getName().compareTo(m2.getName());
            }
        });
    }

    public void sort2 () {
        Toast.makeText (this, "Sort2", Toast.LENGTH_LONG).show();
        adapter.sort(new Comparator<Medicine>() {
            @Override
            public int compare(Medicine m1, Medicine m2) {
                return m1.getDateOfNextRefill().compareTo(m2.getDateOfNextRefill());
            }
        });
    }

    private class MySimpleArrayAdapter extends ArrayAdapter<Medicine> {
        private final Context context;
        private final Medicine[] values;

        public MySimpleArrayAdapter(Context context, Medicine[] values) {
            super(context, R.layout.medicine_list_row, values);
            this.context = context;
            this.values = values;
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
            textView2.setText(values[position].getDosage());

            String s = values[position].getName();
            imageView.setImageResource(R.drawable.ic_launcher);

            return rowView;
        }
    }
}
*/

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ListActivity;
public class MyMedicationsActivity extends ListActivity {
    private TextView text;
    private List<String> listValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        text = (TextView) findViewById(R.id.mainText);

        Medicine med1 = new Medicine ("medicine 1", new GregorianCalendar (2015,3,26,3,1), "1 dose") ;
        Medicine med2 = new Medicine ("medicine 2", new GregorianCalendar (2015,3,26,5,0), "1 dose") ;
        Medicine med3 = new Medicine ("medicine 3", new GregorianCalendar (2015,9,2,12,47), "1 dose") ;
        Medicine med4 = new Medicine ("medicine 4", new GregorianCalendar (2015,3,28,8,9), "1 dose") ;
        Medicine med5 = new Medicine ("medicine 5", new GregorianCalendar (2015,12,28,2,26), "1 dose") ;
        Medicine med6 = new Medicine ("medicine 6", new GregorianCalendar (2015,3,27,7,47), "1 dose") ;
        Medicine med7 = new Medicine ("medicine 7", new GregorianCalendar (2014,3,28,8,9), "1 dose") ;
        Medicine med8 = new Medicine ("medicine 8", new GregorianCalendar (2015,3,28,11,26), "1 dose") ;

        Medicine[] list = new Medicine[] {med1, med2, med3, med4, med5, med6, med7, med8} ;

        // initiate the listadapter
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, list);

        // assign the list adapter
        setListAdapter(adapter);

        addListenerOnSpinnerItemSelection () ;
    }

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

    private void addListenerOnSpinnerItemSelection () {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner) ;
        spinner.setOnItemSelectedListener (new SpinnerListener (this, spinner)) ;
    }

    private class SpinnerListener implements AdapterView.OnItemSelectedListener {

        private MyMedicationsActivity activity ;
        private Spinner spinner ;

        public SpinnerListener (MyMedicationsActivity activity, Spinner spinner) {
            super () ;
            this.activity = activity ;
            this.spinner = spinner ;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selected = spinner.getSelectedItem().toString() ;
            Log.v (this.toString(), selected) ;
            if (selected.equals("Name")) {
                activity.sort1 (spinner) ;
            }
            else if (selected.equals("Date")) {
                activity.sort2 (spinner) ;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    // when an item of the list is clicked
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);

        Medicine selectedItem = (Medicine) getListView().getItemAtPosition(position);
        //String selectedItem = (String) getListAdapter().getItem(position);

        text.setText("You clicked " + selectedItem.getName() + " at position " + position);
    }

    public void sort1 (View view) {
        Log.v(this.toString(), "SORT1") ;
        MySimpleArrayAdapter adapter = (MySimpleArrayAdapter) this.getListAdapter() ;
        adapter.sort(new Comparator<Medicine>() {
            @Override
            public int compare(Medicine m1, Medicine m2) {
                return m1.getName().compareTo(m2.getName());
            }
        });
    }

    public void sort2 (View view) {
        Log.v(this.toString(), "SORT2") ;
        MySimpleArrayAdapter adapter = (MySimpleArrayAdapter) this.getListAdapter() ;
        adapter.sort(new Comparator<Medicine>() {
            @Override
            public int compare(Medicine m1, Medicine m2) {
                return m1.getDateOfNextRefill().compareTo(m2.getDateOfNextRefill());
            }
        });
    }

    private class MySimpleArrayAdapter extends ArrayAdapter<Medicine> {
        private final Context context;
        private final Medicine[] values;

        public MySimpleArrayAdapter(Context context, Medicine[] values) {
            super(context, R.layout.medicine_list_row, values);
            this.context = context;
            this.values = values;
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
            textView2.setText(formatGregorianCalendar(values[position].getDateOfNextRefill()));

            String s = values[position].getName();
            imageView.setImageResource(R.drawable.ic_launcher);

            return rowView;
        }
    }
}