package group38.elderlyportal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;


public class MainActivity extends Activity {

    public static final int MyMedicationsActivity_ID = 1;
    public static final int AddAMedicationActivity_ID = 2;
    public static final int CalendarActivity_ID = 3;
    public static final int SettingsActivity_ID = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myMedications(View view) {
        Intent intent = new Intent(this, MyMedicationsActivity.class);
        startActivityForResult(intent, MyMedicationsActivity_ID);
    }

    public void addAMedication(View view) {
        Intent intent = new Intent(this, AddAMedicationActivity.class);
        startActivityForResult(intent, AddAMedicationActivity_ID);
    }

    public void calendar(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivityForResult(intent, CalendarActivity_ID);
    }

    public void settings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity_ID);
    }
}
