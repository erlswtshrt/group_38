package group38.elderlyportal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;


/**
 * Created by JohnEarle on 3/21/15.
 */
public class CalendarActivity extends Activity {

    private CalendarView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new CalendarView(this);
        setContentView(view);
    }
}