package group38.elderlyportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class EditAMedicationActivity extends Activity {

    private EditAMedicationView view;
    private ArrayList<Dose> doses;
    private Medicine theMedicine ; //the medicine that is being edited

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new EditAMedicationView(this);
        setContentView(view);
        setContentView(R.layout.activity_edit_a_medication);

        //retrieve the medicine from the intent
        theMedicine = (Medicine) getIntent().getExtras().getParcelable("Medicine");

        //set the name field of the form to be the medicine's name
        EditText brandNameField = (EditText) findViewById(R.id.brand_name);
        brandNameField.setText(theMedicine.getBrandName());

        //set the scientific name field
        EditText scientificNameField = (EditText) findViewById(R.id.sci_name);
        scientificNameField.setText(theMedicine.getScientificName());


        EditText instructionsField = (EditText) findViewById(R.id.instructions);
        instructionsField.setText(theMedicine.getInstructions());

        EditText numRefillsField = (EditText) findViewById(R.id.num_refills);
        numRefillsField.setText(theMedicine.getNumRefills());

        EditText refillDateField = (EditText) findViewById(R.id.refill_date);
        //refillDateField.setText(theMedicine.getDateOfNextRefill());
    }

    public void onSaveButtonClick(View view) {
        Medicine oldMedicine = theMedicine;

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

        resultIntent.putExtra("old_medicine", oldMedicine);

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