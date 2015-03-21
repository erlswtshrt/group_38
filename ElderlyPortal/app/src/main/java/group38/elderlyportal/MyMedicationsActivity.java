package group38.elderlyportal;

import android.app.Activity;
import android.os.Bundle;


/**
 * Created by JohnEarle on 3/21/15.
 */
public class MyMedicationsActivity extends Activity {

    private MyMedicationsView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new MyMedicationsView(this);
        setContentView(view);
    }
}
