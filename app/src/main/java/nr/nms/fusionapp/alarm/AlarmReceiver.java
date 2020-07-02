package nr.nms.fusionapp.alarm;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import nr.nms.fusionapp.R;

public class AlarmReceiver extends BroadcastReceiver {

    private PendingIntent pendingIntent;
    private static final int ALARM_REQUEST_CODE = 134;
    private int interval_seconds = 20;
    private NotificationManager alarmNotificationManager;
    String NOTIFICATION_CHANNEL_ID = "rasupe_channel_id";
    String NOTIFICATION_CHANNEL_NAME = "rasupe channel";
    private int NOTIFICATION_ID = 1;
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, alarmIntent, 0);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, interval_seconds);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= 23) {
            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        } else if (android.os.Build.VERSION.SDK_INT >= 19) {
            manager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        } else {
            manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        }

        sendNotification(context, intent);
    }

    private void sendNotification(Context context, Intent itn) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
        String datetimex = sdf.format(new Date());
        String notif_title = "Wake Up!!!";
        String notif_content = "Notif time "+datetimex;
        alarmNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent newIntent = new Intent(context,MainAlarm.class);
        newIntent.putExtra("notifkey", "notifvalue");
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                newIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance);
            alarmNotificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        alamNotificationBuilder.setContentTitle(notif_title);
        alamNotificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        alamNotificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        alamNotificationBuilder.setContentText(notif_content);
        alamNotificationBuilder.setAutoCancel(true);
        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(NOTIFICATION_ID, alamNotificationBuilder.build());
    }

}
