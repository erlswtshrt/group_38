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
        // remove dose from doses list associated with this medication
        Button deleteButton = (Button) view ;
        Dose dose = (Dose) deleteButton.getTag() ;
        doses.remove(dose);

        // get parent of delete button and delete parent view
        ViewParent tempView = view.getParent();
        LinearLayout ll = (LinearLayout) findViewById(R.id.lin);
        ll.removeView((ViewGroup)tempView);
    }

    public void onSaveButtonClick(View view) {
        EditText brandNamField = (EditText) findViewById(R.id.brand_name);
        EditText scientificNameField = (EditText) findViewById(R.id.sci_name);
        EditText instructionsField = (EditText) findViewById(R.id.instructions);
        EditText numRefillsField = (EditText) findViewById(R.id.num_refills);
        EditText refillDateField = (EditText) findViewById(R.id.refill_date);

        String brandName = brandNamField.getText().toString();
        String scientificName = scientificNameField.getText().toString();
        String instructions = instructionsField.getText().toString();
        String numRefills = numRefillsField.getText().toString();
        String refillDate = refillDateField.getText().toString();


        Intent resultIntent = new Intent();
        /*
        resultIntent.putParcelableArrayListExtra("doses", doses);
        resultIntent.putExtra("brandName", brandName);
        resultIntent.putExtra("scientificName", scientificName);
        resultIntent.putExtra("instructions", instructions);
        resultIntent.putExtra("numRefills", numRefills);
        resultIntent.putExtra("refillDate", new GregorianCalendar(2015, 3, 26, 3, 1));
        */
        Medicine addedMedicine = new Medicine (doses, brandName, scientificName, instructions, numRefills, new GregorianCalendar(2015, 3, 26, 3, 1)) ;
        resultIntent.putExtra ("addedMedicine", addedMedicine) ;

        setResult(Activity.RESULT_OK, resultIntent);

        finish();
    }

    public void onClearButtonClick(View view) {
        EditText brandNamField = (EditText) findViewById(R.id.brand_name);
        EditText scientificNameField = (EditText) findViewById(R.id.sci_name);
        EditText instructionsField = (EditText) findViewById(R.id.instructions);
        EditText numRefillsField = (EditText) findViewById(R.id.num_refills);
        EditText refillDateField = (EditText) findViewById(R.id.refill_date);

        // reset input strings
        brandNamField.setText("");
        scientificNameField.setText("");
        instructionsField.setText("");
        numRefillsField.setText("");
        refillDateField.setText("");
    }
}