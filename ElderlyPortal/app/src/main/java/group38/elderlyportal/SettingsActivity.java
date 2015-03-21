package group38.elderlyportal;

import android.app.Activity;
import android.os.Bundle;


/**
 * Created by JohnEarle on 3/21/15.
 */
public class SettingsActivity extends Activity {

    private SettingsView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new SettingsView(this);
        setContentView(view);
    }
}