package nr.nms.fusionapp.Dashboard;

import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import nr.nms.fusionapp.MainActivity;
import nr.nms.fusionapp.R;
import nr.nms.fusionapp.Share.share_preference;
import nr.nms.fusionapp.about;
import nr.nms.fusionapp.alarm.MainAlarm;
import nr.nms.fusionapp.asyntask.MainAsyntask;
import nr.nms.fusionapp.notif.notifyme;

public class MenuDashboard extends AppCompatActivity {


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dashboard_menu);



}

    public void dababase(View view) {
    Intent intent = new Intent(MenuDashboard.this, MainActivity.class);
    startActivity(intent);
    }

    public void about(View view) {
    Intent intent = new Intent(MenuDashboard.this, about.class);
    startActivity(intent);
    startActivity(intent);
    }

    public void notif(View view) {
    Intent intent = new Intent(MenuDashboard.this, notifyme.class);
    startActivity(intent);
    }

    public void prefs(View view) {
    Intent intent = new Intent(MenuDashboard.this, share_preference.class);
    startActivity(intent);
    }

    public void alarm(View view) {
    Intent intent = new Intent(MenuDashboard.this, MainAlarm.class);
    startActivity(intent);
    }

    public void async(View view) {
    Intent intent = new Intent(MenuDashboard.this, MainAsyntask.class);
    startActivity(intent);
    }
}
