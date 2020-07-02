package nr.nms.fusionapp.asyntask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;


public class Async extends AsyncTask<Void,Void, String> {

    private WeakReference<TextView> mTextView;

    Async(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {

        //menghasilkan bilangan acak dari 0-10
        Random r = new Random();
        int n = r.nextInt(11);

        int s = n * 200;

        try{
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Awake at last after sleeping for " + s + "milliseconds!";

    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
