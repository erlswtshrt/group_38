package group38.elderlyportal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * Created by JohnEarle on 3/21/15.
 */
public class SettingsActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private SettingsView view;

    private String selectedTextSize;
    private String selectedAutoAlerts;
    private String selectedReminderPreference;
    private String selectedLongTermReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new SettingsView(this);
        setContentView(view);
        setContentView(R.layout.activity_settings);

        Spinner textSizeSpinner = (Spinner) findViewById(R.id.text_size_spinner);
        ArrayAdapter<CharSequence> textSizeAdapter = ArrayAdapter.createFromResource(
                this, R.array.text_size_array, android.R.layout.simple_spinner_item);
        textSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textSizeSpinner.setAdapter(textSizeAdapter);
        textSizeSpinner.setOnItemSelectedListener(this);
        textSizeSpinner.setSelection(1);

        Spinner autoAlertsSpinner = (Spinner) findViewById(R.id.auto_alerts_spinner);
        ArrayAdapter<CharSequence> autoAlertsAdapter = ArrayAdapter.createFromResource(
                this, R.array.auto_alerts_array,android.R.layout.simple_spinner_item);
        autoAlertsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        autoAlertsSpinner.setAdapter(autoAlertsAdapter);
        autoAlertsSpinner.setOnItemSelectedListener(this);

        Spinner reminderPreferenceSpinner = (Spinner) findViewById(R.id.reminder_preference_spinner);
        ArrayAdapter<CharSequence> reminderPreferenceAdapter = ArrayAdapter.createFromResource(
                this, R.array.reminder_preference_array,android.R.layout.simple_spinner_item);
        reminderPreferenceAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        reminderPreferenceSpinner.setAdapter(reminderPreferenceAdapter);
        reminderPreferenceSpinner.setOnItemSelectedListener(this);
        reminderPreferenceSpinner.setSelection(1);

        Spinner longTermPreferenceSpinner = (Spinner) findViewById(R.id.long_term_reminder_spinner);
        ArrayAdapter<CharSequence> longTermPreferenceAdapter = ArrayAdapter.createFromResource(
                this, R.array.long_term_reminder_array,android.R.layout.simple_spinner_item);
        longTermPreferenceAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        longTermPreferenceSpinner.setAdapter(longTermPreferenceAdapter);
        longTermPreferenceSpinner.setOnItemSelectedListener(this);
        longTermPreferenceSpinner.setSelection(1);
    }

    public void onSetReminderTimesButtonClick(View view) {

    }

    public void onEditAlertsButtonClick(View view) {

    }

    public void onShareHistoryButtonClick(View view) {

    }

    public void onMainMenuButtonClick(View view) {
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        switch(parent.getId()) {
            case R.id.text_size_spinner :
                selectedTextSize = spinner.getSelectedItem().toString();
                break;
            case R.id.auto_alerts_spinner :
                selectedAutoAlerts = spinner.getSelectedItem().toString();
                break;
            case R.id.reminder_preference_spinner :
                selectedReminderPreference = spinner.getSelectedItem().toString();
                break;
            case R.id.long_term_reminder_spinner :
                selectedLongTermReminder = spinner.getSelectedItem().toString();
                break;
            default :
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}