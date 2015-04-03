package group38.elderlyportal;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * Created by JohnEarle on 3/21/15.
 */
public class SettingsActivity extends FontSizeActivity implements AdapterView.OnItemSelectedListener {

    private SettingsView view;

    private String selectedTextSize;
    private String selectedAutoAlerts;
    private String selectedReminderPreference;
    private String selectedLongTermReminder;

    public static final String FONT_SIZE = "FontSize";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new SettingsView(this);
        setContentView(view);
        setContentView(R.layout.activity_settings);

        Bundle extras = getIntent().getExtras();
        selectedTextSize = extras.getString("TextSize");
        selectedAutoAlerts = extras.getString("AutoAlerts");
        selectedReminderPreference = extras.getString("ReminderPreference");
        selectedLongTermReminder = extras.getString("LongTermReminder");

        Spinner textSizeSpinner = (Spinner) findViewById(R.id.text_size_spinner);
        ArrayAdapter<CharSequence> textSizeAdapter = ArrayAdapter.createFromResource(
                this, R.array.text_size_array, android.R.layout.simple_spinner_item);
        textSizeAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        textSizeSpinner.setAdapter(textSizeAdapter);
        textSizeSpinner.setOnItemSelectedListener(this);
        if (selectedTextSize != null) {
            int spinnerPosition = textSizeAdapter.getPosition(selectedTextSize);
            textSizeSpinner.setSelection(spinnerPosition);
        }

        Spinner autoAlertsSpinner = (Spinner) findViewById(R.id.auto_alerts_spinner);
        ArrayAdapter<CharSequence> autoAlertsAdapter = ArrayAdapter.createFromResource(
                this, R.array.auto_alerts_array, android.R.layout.simple_spinner_item);
        autoAlertsAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        autoAlertsSpinner.setAdapter(autoAlertsAdapter);
        autoAlertsSpinner.setOnItemSelectedListener(this);
        if (selectedAutoAlerts != null) {
            int spinnerPosition = autoAlertsAdapter.getPosition(selectedAutoAlerts);
            autoAlertsSpinner.setSelection(spinnerPosition);
        }

        Spinner reminderPreferenceSpinner = (Spinner) findViewById(R.id.reminder_preference_spinner);
        ArrayAdapter<CharSequence> reminderPreferenceAdapter = ArrayAdapter.createFromResource(
                this, R.array.reminder_preference_array, android.R.layout.simple_spinner_item);
        reminderPreferenceAdapter.setDropDownViewResource(
                android.R.layout.simple_list_item_activated_1);
        reminderPreferenceSpinner.setAdapter(reminderPreferenceAdapter);
        reminderPreferenceSpinner.setOnItemSelectedListener(this);
        if (selectedReminderPreference != null) {
            int spinnerPosition = reminderPreferenceAdapter.getPosition(selectedReminderPreference);
            reminderPreferenceSpinner.setSelection(spinnerPosition);
        }

        Spinner longTermPreferenceSpinner = (Spinner) findViewById(R.id.long_term_reminder_spinner);
        ArrayAdapter<CharSequence> longTermPreferenceAdapter = ArrayAdapter.createFromResource(
                this, R.array.long_term_reminder_array, android.R.layout.simple_spinner_item);
        longTermPreferenceAdapter.setDropDownViewResource(
                android.R.layout.simple_list_item_activated_1);
        longTermPreferenceSpinner.setAdapter(longTermPreferenceAdapter);
        longTermPreferenceSpinner.setOnItemSelectedListener(this);
        if (selectedLongTermReminder != null) {
            int spinnerPosition = longTermPreferenceAdapter.getPosition(selectedLongTermReminder);
            longTermPreferenceSpinner.setSelection(spinnerPosition);
        }
    }

    public void onSetReminderTimesButtonClick(View view) {

    }

    public void onEditAlertsButtonClick(View view) {

    }

    public void onShareHistoryButtonClick(View view) {

    }

    public void onMainMenuButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("TextSize", selectedTextSize);
        intent.putExtra("AutoAlerts", selectedAutoAlerts);
        intent.putExtra("ReminderPreference", selectedReminderPreference);
        intent.putExtra("LongTermReminder", selectedLongTermReminder);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        switch(parent.getId()) {
            case R.id.text_size_spinner :
                selectedTextSize = spinner.getSelectedItem().toString();
                updatePreferences();
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

    private void updatePreferences() {
        SharedPreferences settings = getSharedPreferences(FONT_SIZE, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("FontSize", selectedTextSize);
        editor.commit();
    }
}