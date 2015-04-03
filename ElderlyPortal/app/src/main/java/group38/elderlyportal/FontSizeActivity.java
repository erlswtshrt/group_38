package group38.elderlyportal;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by Neil on 4/3/2015.
 */
public class FontSizeActivity extends Activity {
    @Override
    public void onStart() {
        super.onStart();

        try {
            SharedPreferences settings =
                    getSharedPreferences(SettingsActivity.FONT_SIZE, MODE_PRIVATE);

            String fontSizePref = settings.getString("FontSize", "Medium");

            int themeID = R.style.FontSizeMedium;   // default
            switch (fontSizePref) {
                case "Small" :
                    themeID = R.style.FontSizeSmall;
                    break;
                case "Large" :
                    themeID = R.style.FontSizeLarge;
                    break;
                default :
                    break;
            }

            setTheme(themeID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
