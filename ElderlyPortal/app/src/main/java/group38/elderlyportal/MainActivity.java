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

    private String selectedTextSize;
    private String selectedAutoAlerts;
    private String selectedReminderPreference;
    private String selectedLongTermReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        medList.add(new Medicine (new ArrayList<Dose> (), "Medicine 1", "Sci Name A", "Instructions", "5", new GregorianCalendar(2015, 3, 26, 1, 1)));
        medList.add(new Medicine (new ArrayList<Dose> (), "Medicine 2", "Sci Name G", "Instructions", "5", new GregorianCalendar(2015, 3, 27, 3, 1)));
        medList.add(new Medicine (new ArrayList<Dose> (), "Medicine 3", "Sci Name H", "Instructions", "5", new GregorianCalendar(2015, 3, 26, 6, 1)));
        medList.add(new Medicine (new ArrayList<Dose> (), "Medicine 4", "Sci Name E", "Instructions", "5", new GregorianCalendar(2015, 3, 27, 3, 1)));
        medList.add(new Medicine (new ArrayList<Dose> (), "Medicine 5", "Sci Name F", "Instructions", "5", new GregorianCalendar(2015, 3, 26, 9, 1)));
        medList.add(new Medicine (new ArrayList<Dose> (), "Medicine 6", "Sci Name V", "Instructions", "5", new GregorianCalendar(2015, 3, 28, 3, 1)));
        medList.add(new Medicine (new ArrayList<Dose> (), "Medicine 7", "Sci Name B", "Instructions", "5", new GregorianCalendar(2015, 3, 26, 14, 1)));
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
                if (resultCode == Activity.RESULT_OK) {
                    Medicine temp = intent.getExtras().getParcelable("addedMedicine");
                    medList.add(temp);
                }
                break;
            }
            case (MyMedicationsActivity_ID): {
                if (resultCode == Activity.RESULT_OK) {
                    medList = intent.getParcelableArrayListExtra("list");
                }
                break;
            }
            case (SettingsActivity_ID): {
                if (resultCode == Activity.RESULT_OK) {
                    selectedTextSize = intent.getStringExtra("TextSize");
                    selectedAutoAlerts = intent.getStringExtra("AutoAlerts");
                    selectedReminderPreference = intent.getStringExtra("ReminderPreference");
                    selectedLongTermReminder = intent.getStringExtra("LongTermReminder");
                }
                break;
            }
            default:
                break;
        }
    }

    public void calendar(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivityForResult(intent, CalendarActivity_ID);
    }

    public void settings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("TextSize", selectedTextSize);
        intent.putExtra("AutoAlerts", selectedAutoAlerts);
        intent.putExtra("ReminderPreference", selectedReminderPreference);
        intent.putExtra("LongTermReminder", selectedLongTermReminder);
        startActivityForResult(intent, SettingsActivity_ID);
    }
}
