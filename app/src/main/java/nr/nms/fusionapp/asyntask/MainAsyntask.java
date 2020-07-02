package nr.nms.fusionapp.asyntask;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import nr.nms.fusionapp.R;

public class MainAsyntask extends AppCompatActivity {

    private TextView mTextView;

    private static final String TEXT_STATE = "currentText";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asyntask_main);

        mTextView = findViewById(R.id.textAsyntask);

        if(savedInstanceState!=null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }


    }

    public void startTask(View view) {
        mTextView.setText(R.string.ready_to_start);

        new Async(mTextView).execute();
    }


    protected void onSaveInstantanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE,
                mTextView.getText().toString());
    }

}
