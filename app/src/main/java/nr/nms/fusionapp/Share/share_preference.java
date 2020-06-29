package nr.nms.fusionapp.Share;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import nr.nms.fusionapp.R;

public class share_preference extends AppCompatActivity implements View.OnClickListener{


    private Button b1;
    private int mColor;
    private final String COLOR_KEY = "color";


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.prefs);


        b1 = findViewById(R.id.Button1);
        b1.setOnClickListener(this);

        //Sharred Preferences

        mColor = ContextCompat.getColor(this, R.color.default_background);

        // Restore the saved instance state.
        if (saveInstanceState != null) {

            mColor = saveInstanceState.getInt(COLOR_KEY);
            b1.setBackgroundColor(mColor);
        }

    }

    //Shared Preferences
    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        b1.setBackgroundColor(mColor);
        mColor = color;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(COLOR_KEY, mColor);
    }


    @Override
    public void onClick(View v) {

    }
}
