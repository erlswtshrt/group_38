package group38.elderlyportal;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import android.widget.AdapterView.OnItemSelectedListener;


/**
 * Created by JohnEarle on 3/21/15.
 */
public class AddADoseActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private int time = 0;

    private int numOfPills = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_a_dose);

        Spinner timeSpinner = (Spinner) findViewById(R.id.timeSpinner);
        Spinner numOfPillsSpinner = (Spinner) findViewById(R.id.numOfPillsSpinner);

        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(
                this, R.array.time_array,android.R.layout.simple_spinner_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);
        timeSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> numOfPillsAdapter = ArrayAdapter.createFromResource(
                this, R.array.numOfPills_array,android.R.layout.simple_spinner_item);
        numOfPillsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numOfPillsSpinner.setAdapter(numOfPillsAdapter);
        numOfPillsSpinner.setOnItemSelectedListener(this);
    }

    public void onSaveButtonClick(View view) {

        Intent resultIntent = new Intent();

        resultIntent.putExtra("time", time);
        resultIntent.putExtra("numOfPills", numOfPills);

        setResult(Activity.RESULT_OK, resultIntent);

        finish();
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        int spinnerId = parent.getId();
        if(spinnerId == 2131296319) { time = pos; }
        else { numOfPills = pos; }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
