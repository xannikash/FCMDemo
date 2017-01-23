package demo.techtime.com.fcmdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.techtime.fcmdemo.R;

/**
 * Created by Ashwin Ukey on 7/01/17.
 */
public class FCMService extends FirebaseMessagingService {

    private static final String TAG = FCMService.class.getSimpleName();

    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage == null)
            return;
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            generateNotification(getApplication(), remoteMessage.getNotification().getBody());
        }

    }

    private static void generateNotification(Context context, String message) {
        int icon = R.drawable.btnnotification;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);

        String title = context.getString(R.string.app_name);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.putExtra("ms", message);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
//        notification.setLatestEventInfo(context, title, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notification.defaults |= Notification.DEFAULT_SOUND;

        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(0, notification);
    }

}
