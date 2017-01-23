package demo.techtime.com.fcmdemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by Ashwin Ukey on 7/01/17.
 */

public class FCMbroadcast extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            ComponentName comp = new ComponentName(context.getPackageName(),
                    FCMService.class.getName());
            startWakefulService(context, (intent.setComponent(comp)));
            setResultCode(Activity.RESULT_OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
