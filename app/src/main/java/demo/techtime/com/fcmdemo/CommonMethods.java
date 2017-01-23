package demo.techtime.com.fcmdemo;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by Ashwin Ukey on 1/7/2017.
 */

public class CommonMethods {
    public static ProgressDialog progressDialog;

    public static void showProgressDialog(final Activity activity, final String title, final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog = ProgressDialog.show(activity, title, message);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
            }
        });
    }

    public static void changeProgressDialogMessage(final Activity activity, final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog != null) {
                    progressDialog.setMessage(message);
                }
            }
        });
    }

    public static void hideProgressDialog(Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog != null) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            }
        });
    }
}
