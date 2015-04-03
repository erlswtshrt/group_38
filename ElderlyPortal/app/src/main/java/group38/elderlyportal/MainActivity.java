package group38.elderlyportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class MainActivity extends Activity {

    public static final int MyMedicationsActivity_ID = 1;
    public static final int AddAMedicationActivity_ID = 2;
    public static final int CalendarActivity_ID = 3;
    public static final int SettingsActivity_ID = 4;
    ArrayList<Medicine> medList = new ArrayList<Medicine>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myMedications(View view) {
        Intent intent = new Intent(this, MyMedicationsActivity.class);
        intent.putParcelableArrayListExtra("list", medList);
        startActivityForResult(intent, MyMedicationsActivity_ID);
    }

    public void addAMedication(View view) {
        Intent intent = new Intent(this, AddAMedicationActivity.class);
        intent.putParcelableArrayListExtra("list", medList);
        startActivityForResult(intent, AddAMedicationActivity_ID);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case (AddAMedicationActivity_ID): {
//                if (resultCode == Activity.RESULT_OK) {
//                    String name = intent.getStringExtra("med_name"); //need to get other extras here
//                    Medicine temp = new Medicine(name, new GregorianCalendar(2015, 3, 26, 3, 1), "2 doses");
//                    medList.add(temp);
//                }
                break;
            }
            case (MyMedicationsActivity_ID): {
                if (resultCode == Activity.RESULT_OK) {
                    medList = intent.getParcelableArrayListExtra("list");
                }
            }
            break;
        }
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
