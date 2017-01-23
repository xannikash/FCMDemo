package demo.techtime.com.fcmdemo;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.techtime.fcmdemo.R;

import static demo.techtime.com.fcmdemo.Config.SH_REGID;

public class MainActivity extends AppCompatActivity {
    TextView textview_fcmtoken;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview_fcmtoken = (TextView) findViewById(R.id.textview_fcmtoken);
        button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call asynctask to register device to FCM server
                new FCM_task().execute();
            }
        });


        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String fcm_token = pref.getString(SH_REGID, "");
        textview_fcmtoken.setText("FCM TOKEN : \n" + fcm_token + "");
    }


    public class FCM_task extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            CommonMethods.changeProgressDialogMessage(MainActivity.this, "Registering on google server.");
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
                String fcm_token = pref.getString(SH_REGID, "");
                if (fcm_token.length() > 0) return fcm_token;
                else {
                    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putString("regId", refreshedToken).commit();
                    return refreshedToken;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this, "FCM TOKEN :" + s, Toast.LENGTH_SHORT).show();
            textview_fcmtoken.setText("FCM TOKEN : \n" + s + "");
            super.onPostExecute(s);
        }
    }
}
