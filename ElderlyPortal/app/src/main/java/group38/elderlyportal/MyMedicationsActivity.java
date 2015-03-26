package group38.elderlyportal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.app.ListActivity;
import android.os.Bundle;
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


/**
 * Created by JohnEarle on 3/21/15.
 */
//public class MyMedicationsActivity extends ListActivity {

    /* OLD STUFF
    private MyMedicationsView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new MyMedicationsView(this);
        setContentView(view);
    }
    */

    /*
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        final ArrayList<Medicine> list = new ArrayList<Medicine>();
        Medicine med1 = new Medicine ("medicine 1", new GregorianCalendar(), "Monday") ;
        Medicine med2 = new Medicine ("medicine 2", new GregorianCalendar(), "Tuesday") ;
        Medicine med3 = new Medicine ("medicine 3", new GregorianCalendar(), "Wednesday") ;
        list.add (med1) ;
        list.add (med2) ;
        list.add (med2) ;

        // use your custom layout
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this,
                R.layout.medicine_list_row, R.id.label, list);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }

    private class StableArrayAdapter extends ArrayAdapter<Medicine> {

        HashMap<Medicine, Integer> mIdMap = new HashMap<Medicine, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<Medicine> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            Medicine item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    public class MySimpleArrayAdapter extends ArrayAdapter<Medicine> {
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
            TextView textView = (TextView) rowView.findViewById(R.id.label);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            textView.setText(values[position].getName());
            // change the icon for Windows and iPhone
            Medicine s = values[position];
            if (s.getName().startsWith("iPhone")) {
                imageView.setImageResource(R.drawable.ic_launcher);
            } else {
                imageView.setImageResource(R.drawable.ic_launcher);
            }

            return rowView;
        }
    }

}
*/

public class MyMedicationsActivity extends ListActivity {
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        Medicine med1 = new Medicine ("medicine 1", new GregorianCalendar(), "Monday") ;
        Medicine med2 = new Medicine ("medicine 2", new GregorianCalendar(), "Tuesday") ;
        Medicine med3 = new Medicine ("medicine 3", new GregorianCalendar(), "Wednesday") ;

        Medicine[] list = new Medicine[] {med1, med2, med3} ;
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, list);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Medicine item = (Medicine) getListAdapter().getItem(position);
        Toast.makeText(this, item.getName() + " selected", Toast.LENGTH_LONG).show();
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
            TextView textView = (TextView) rowView.findViewById(R.id.label);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            textView.setText(values[position].getName());
            // Change the icon for Windows and iPhone
            String s = values[position].getName();
            if (s.startsWith("Windows7") || s.startsWith("iPhone")
                    || s.startsWith("Solaris")) {
                imageView.setImageResource(R.drawable.ic_launcher);
            } else {
                imageView.setImageResource(R.drawable.ic_launcher);
            }

            return rowView;
        }
    }
}