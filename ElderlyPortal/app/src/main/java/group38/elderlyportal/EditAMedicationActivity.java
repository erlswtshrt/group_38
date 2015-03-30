package group38.elderlyportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class EditAMedicationActivity extends Activity {

    private EditAMedicationView view;
    private Medicine theMedicine ; //the medicine that is being edited

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new EditAMedicationView(this);
        setContentView(view);
        setContentView(R.layout.activity_edit_a_medication);

        //retrieve the medicine from the intent
        theMedicine = (Medicine) getIntent().getExtras().getParcelable("Medicine") ;

        //set the name field of the form to be the medicine's name
        EditText med_name_Field = (EditText) findViewById(R.id.med_name) ;
        med_name_Field.setText (theMedicine.getName()) ;

        //set the dosage field
        EditText med_dosage_Field = (EditText) findViewById(R.id.dose);
        med_dosage_Field.setText(theMedicine.getDosage());
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