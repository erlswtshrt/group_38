package group38.elderlyportal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.GregorianCalendar;

import group38.elderlyportal.Medicine;
import group38.elderlyportal.R;

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