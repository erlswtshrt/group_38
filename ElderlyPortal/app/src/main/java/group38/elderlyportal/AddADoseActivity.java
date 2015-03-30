package group38.elderlyportal;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.GregorianCalendar;


/**
 * Created by JohnEarle on 3/21/15.
 */
public class AddADoseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_a_dose);
    }

    public void onSaveButtonClick(View view) {
        EditText time_field = (EditText) findViewById(R.id.time);
        EditText num_of_pills_field = (EditText) findViewById(R.id.numOfPills);

        String time = time_field.getText().toString();
        String numOfPills = num_of_pills_field.getText().toString();


        Intent resultIntent = new Intent();

        resultIntent.putExtra("time", time);
        resultIntent.putExtra("numOfPills", numOfPills);

        setResult(Activity.RESULT_OK, resultIntent);

        finish();
    }
}
