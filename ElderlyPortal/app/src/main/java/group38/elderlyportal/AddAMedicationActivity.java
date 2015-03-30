package group38.elderlyportal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.GregorianCalendar;


/**
 * Created by JohnEarle on 3/21/15.
 */
public class AddAMedicationActivity extends Activity {

    private AddAMedicationView view;

    private ArrayList<Dose> doses ;

    public static final int AddADoseActivity_ID = 5;

    private DoseListArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new AddAMedicationView(this);
        setContentView(R.layout.activity_add_a_medication);

        // init ArrayList of doses
        doses = new ArrayList<Dose> () ;

        // initiate the listadapter to be used for managing the list of doses
        adapter = new DoseListArrayAdapter(this, doses);

    }

    public void onAddADoseButtonClick(View view) {
        Intent intent = new Intent(this, AddADoseActivity.class);
        startActivityForResult(intent, AddADoseActivity_ID);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == AddADoseActivity_ID) {
            if(resultCode == RESULT_OK){
                int time=data.getIntExtra("time", 0);
                int numOfPills=data.getIntExtra("numOfPills", 0);

                doses.add(new Dose(time, numOfPills));

                LinearLayout ll = (LinearLayout) findViewById(R.id.lin);
                View v = adapter.getView(adapter.getCount()-1, null, null);
                ll.addView(v);
            }
            if (resultCode == RESULT_CANCELED) {
                //NO RESULT
            }
        }
    }

    public void deleteDose (View view) {
        // get parent of delete button and delete parent view
        /* A test method to be used for debugging.
       @TODO remove dose from ArrayList
        */
        ViewParent tempView = view.getParent();
        LinearLayout ll = (LinearLayout) findViewById(R.id.lin);
        ll.removeView((ViewGroup)tempView);

    }

    public void onSaveButtonClick(View view) {
        EditText med_name_Field = (EditText) findViewById(R.id.med_name);
        EditText dose_Field = (EditText) findViewById(R.id.dose);
        EditText how_often_Field = (EditText) findViewById(R.id.how_often);
        EditText tid_Field = (EditText) findViewById(R.id.TID_Code);
        EditText bid_Field = (EditText) findViewById(R.id.BID_Code);
        EditText num_refills_Field = (EditText) findViewById(R.id.num_refills);
        EditText med_notes_Field = (EditText) findViewById(R.id.med_notes);

        String med_name = med_name_Field.getText().toString();
        String dose = dose_Field.getText().toString();
        String how_often = how_often_Field.getText().toString();
        String tid = tid_Field.getText().toString();
        String bid = bid_Field.getText().toString();
        String num_refills = num_refills_Field.getText().toString();
        String med_notes = med_notes_Field.getText().toString();


        Intent resultIntent = new Intent();

        resultIntent.putExtra("med_name", med_name);
        resultIntent.putExtra("dose", dose);
        resultIntent.putExtra("how_often", how_often);
        resultIntent.putExtra("tid", tid);
        resultIntent.putExtra("bid", bid);
        resultIntent.putExtra("num_refills", num_refills);
        resultIntent.putExtra("med_notes", med_notes);

        setResult(Activity.RESULT_OK, resultIntent);

        finish();
    }

    public void onClearButtonClick(View view) {
        EditText med_name_Field = (EditText) findViewById(R.id.med_name);
        EditText dose_Field = (EditText) findViewById(R.id.dose);
        EditText how_often_Field = (EditText) findViewById(R.id.how_often);
        EditText tid_Field = (EditText) findViewById(R.id.TID_Code);
        EditText bid_Field = (EditText) findViewById(R.id.BID_Code);
        EditText num_refills_Field = (EditText) findViewById(R.id.num_refills);
        EditText med_notes_Field = (EditText) findViewById(R.id.med_notes);

        med_name_Field.setText("");
        dose_Field.setText("");
        how_often_Field.setText("");
        tid_Field.setText("");
        bid_Field.setText("");
        num_refills_Field.setText("");
        med_notes_Field.setText("");
    }

}